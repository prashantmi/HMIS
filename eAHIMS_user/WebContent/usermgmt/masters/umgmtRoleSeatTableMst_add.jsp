


<!-- This file is changed by Adil Wasi for Pagination on DataSet combo on 05-Apr-2013 -->




<jsp:useBean id="newseat" class="usermgmt.masters.UmgmtRoleSeatTableMstBean" scope="request"/>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<%
String hmode = "";
String title = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";

}
%>	
<%
String hospitalcode =(String)session.getAttribute("HOSPITAL_CODE");
System.out.println("Hospital"+hospitalcode);

String seatId =(String)session.getAttribute("SEAT_ID");
System.out.println("seat Id is"+seatId);
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Role Seat Table Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- <script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>-->

<style type="text/css">


	a:link { color: blue; size: 2px; };

	a:link,a:visited {color: green; size: 2px; };
	a:link,a:hover { color: green; size: 2px;};

	a:link,a:active { color: black; size: 10px;}

</style>
<script language="JavaScript" src="../js/util.js" type="text/JavaScript"></script>

<script>
window.history.forward();


function focusOnLoad()
	{
	//alert('table---'+document.forms[0].table[0].value);
//alert('table2---'+document.getElementsByName('table')[0].value);
changeALinkCSS();
	if(document.forms[0].group.value=="0")
	    	document.forms[0].group.focus();
		else 
	if(document.forms[0].seat.value=="0")
	    	document.forms[0].seat.focus();
	    	else 
	if(document.forms[0].table[0].value=="0")
	    	document.forms[0].table[0].focus();
	    else
	    	document.forms[0].firstSeat.focus();
	    	

}    

function savePage(e,formObj,hmode)
{

if(e.type=="click" || e.keyCode==13)
 {	
	var listObj = document.form1.secondSeat;
	var str ="";
	var strName ="";
	for(var i=0;i<listObj.length;i++)
	{
//	alert("listObj[i].value----------"+listObj[i].value);
//	alert("listObj[i].name----------"+listObj[i].text);
	
		str += listObj[i].value+"^";
		strName += listObj[i].text+"^";	
	}
	
	
	if(document.getElementsByName("group")[0].selectedIndex=="0")
	      {
			alert("Group Combo is Mandatory field");
		    document.form1.module[0].focus();
		  }	
		else 
 if(document.getElementsByName("seat")[0].value=="0")
          {
			alert("Seat Combo is Mandatory field");
		  document.form1.seat.focus();
		  }	
	else 
 if(document.getElementsByName("table")[0].value=="0")
          {
			alert("Table Combo is Mandatory field");
	      document.form1.table[0].focus();
	      }
	else 
  if (str=="")
         {
				alert("Data in RightList Box must exist");
				document.form1.secondSeat.focus();	
	     }					
	else
	{
	document.form1.selectedValues.value =str;
	document.form1.selectedNames.value =strName;
	
	
	document.form1.hmode.value = hmode;
	document.form1.submit();
	}
	
  }	
}


function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.form1.hmode.value = hmode;
		document.form1.submit();
		
	}
}	
function submitMode(hmode)
{	
	//alert("inside submitMode-->> mode "+hmode);
    document.forms[0].table[0].value=0;
  	document.forms[0].hmode.value = hmode;	
	document.forms[0].submit();
	
}

function submitModeTable(hmode)
{	
   
    document.forms[0].hmode.value = hmode;	
	document.forms[0].submit();
	
}

function moveRightSingle(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		}
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}
function moveRightAll(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}



function deleteThis(srcMenuObj,targetMenuObj)
{	
	
	
	var t =0;
	var val1 = "";
	var val2 = "";	
	
	var len  = targetMenuObj.length;

	var len2 = srcMenuObj.length;
	
		
	var a1 = new Array(len);
	var a2 = new Array(len);
	
	var a3 = new Array(len2);
	
	
	
	for(var i=0;i<len;i++)
	{		
		a1[i]= targetMenuObj.options[i].value;
		a2[i]= targetMenuObj.options[i].text;	
		
	}
	
	
	for( i=0;i<len2;i++)
	{		
		a3[i]= srcMenuObj.options[i].value;
	}
	
	
	
	targetMenuObj.length = 0;
	
	var counter = 0;
	
	
	var k = 0;
	
	var flag = 0;
		
	for(i=0;i<len;i++)
	{		
		flag = 0;
		for(k=0;k<len2;k++)
		{		
			if(a1[i]==a3[k])
			{	
				flag = 1;					
			}					
		}		
		if(flag==0)
		{
			targetMenuObj.length = (counter+1);
			targetMenuObj.options[counter].value = a1[i];
			targetMenuObj.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
	
}

function moveLeftSingle(listNo)
{
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		if(secondMenuObj.options[i].selected)
		{
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function moveLeftAll(listNo)
{
	
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.location.href="umgmtRoleSeatTableMst_cnt.jsp";
		
	}
}

function cancelForm(e)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			   document.forms[0].action="../../startup/content.jsp";
			   document.forms[0].submit();
			
		}		
	}
	
function checkBeforeMoveLeftSingle()
{
	//alert("inside checkBeforeMoveLeftSingle()")
	var strSecondSeat=document.getElementsByName("secondSeat")[0];
	var strAlpha=document.getElementsByName("strAlphbet")[0].value;
	//alert("alpha :"+strAlpha);
	//alert("strSecondSeat.length :"+strSecondSeat.length);
	if(strSecondSeat!=null && strSecondSeat.length>0)
	{
		for(var j=0; j<strSecondSeat.length; j++)
		{
			if(strSecondSeat.options[j].selected)
			{
				if(strAlpha!='%'){
				if(strSecondSeat.options[j].text.charAt(0)!=strAlpha)
				{
					alert("Dataset, Only starting with Currently selected Alaphabet('"+strAlpha+"') can be moved to Left. ");
					return false;
				}
				}
			}
		}
	}
	moveLeftSingle("1");
}

function checkBeforeMoveLeftAll()
{
	var strSecondSeat=document.getElementsByName("secondSeat")[0];
	var strAlpha=document.getElementsByName("strAlphbet")[0].value;
	//alert("alpha :"+strAlpha);
	//alert("strSecondSeat.length :"+strSecondSeat.length);
	if(strSecondSeat!=null && strSecondSeat.length>0)
	{
		for(var j=0; j<strSecondSeat.length; j++)
		{
			//alert("char at 0 :"+strSecondSeat.options[j].text.charAt(0))
			if(strSecondSeat.options[j].text.charAt(0)!=strAlpha)
			{
				alert("Datasets, Only starting with Currently selected Alaphabet('"+strAlpha+"') can be moved to Left. ");
				return false;
			}
		}
	}
	moveLeftAll("1");
}
function ReplaceAll(Source,stringToFind,stringToReplace){

	  var temp = Source;

	    var index = temp.indexOf(stringToFind);

	        while(index != -1){

	            temp = temp.replace(stringToFind,stringToReplace);

	            index = temp.indexOf(stringToFind);

	        }

	        return temp;

	}
	
function changeALinkCSS()	
{
	var aId=document.getElementsByName("strAlphbet")[0].value+"Id";
	
	var prevAId= document.getElementsByName("strPrevAlphbet")[0].value+"Id";
	//switch(linkName){
	  //alert("Previous:"+document.getElementById(prevAId).className);
	  //alert("Current:"+document.getElementById(aId).className);
		document.getElementById(prevAId).className="link";
		document.getElementById(aId).className="active";
	//}
}
function getLeftAndRightTableColumn(alpha){
	//alert("inside"+alpha);
	if(alpha=='%'){
		if(document.getElementsByName("tablelength")[0].value>200)
		{	
			alert('Cannot show All items as the list is too large.. Please select alphabetical wise')
			return false;
		}
	}
	if(document.getElementsByName("strAlphbet")[0].value==alpha)
		{
			alert("Cuurent Letter selection is same as previous. Please Select Another Letter For Filter.");
			return false;
		}
	var strGroup=document.forms[0].group.value;
	var strSeat=document.forms[0].seat.value;
	var strModule=document.getElementsByName("module")[0].value;
	//alert(strModule);
	var strTable=document.getElementsByName("table")[0].value;
	//alert(strTable);
	var strSecondSeat=document.getElementsByName("secondSeat")[0];
	var strRightColumnList="";
	//alert("strSecondSeat.length :"+strSecondSeat.length);
	for(var i=0; i<strSecondSeat.length; i++)
	{
		if(i==0){
			strRightColumnList=strSecondSeat[i].value;
		}else{
			strRightColumnList=strRightColumnList+","+strSecondSeat[i].value;
		}
	}
	var hospitalcode = document.getElementsByName("hospitalcode")[0].value;
	document.getElementsByName("strPrevAlphbet")[0].value=document.getElementsByName("strAlphbet")[0].value;
	//alert(document.getElementsByName("strPrevAlphbet")[0].value)
	document.getElementsByName("strAlphbet")[0].value=alpha;
	
	var strTableWithDolorSeparation=ReplaceAll(strTable,"#","$");
		
	
	var url="umgmtRoleSeatTableMst_addResponse.jsp?ajaxHmode=GETLEFTCOLUMN&strAlphabet="+alpha+"&strGroup="+strGroup+"&strSeat="+strSeat+"&hospitalcode="+hospitalcode+"&strTable="+strTableWithDolorSeparation+"&strRightColumnList="+strRightColumnList+"&strModule="+strModule;
	//alert("url :"+url)
	ajaxFunction(url,"1");
	
}


function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{
		//alert(res);			  
		// var i;
	  	// var sp = "^";
		// var temp = res.split(sp);
	 	// for(i =0; i < temp.length ; i++)
	    // alert(temp[i]);
	    // document.forms[0].ufullName.value=temp[0];
	    // document.forms[0].designation.value=temp[1];
	    // document.forms[0].effDate.value=temp[2];
	    // document.forms[0].expDate.value=temp[3];
	    //alert("divLeftColumnId innerHTML earlier :"+document.getElementById("divLeftColumnId").innerHTML);
	     document.getElementById("divLeftColumnId").innerHTML=res;
	     changeALinkCSS();
	    // alert("divLeftColumnId innerHTML earlier :"+document.getElementById("divLeftColumnId").innerHTML);
	     
	}
}
</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>
<!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="umgmtRoleSeatTableMst_cnt.jsp">		

  
	<table class="topmargin" width="97%" align="center" cellspacing="0" cellpadding="0">
    	 <tr> 
			<td class="ShadedSubTitleTagImage" colspan=2 align="left">Seat Permission Master </td>
	 	</tr> 
	</table>
	
	 <table class="formbg" width="97%" align="center" cellspacing="0" cellpadding="0">	
	<tr>		
	<td class="label"  width="40%"> 
	<div align="right"><font color='red'>*</font>Group</div>
	</td>
	
	<td class="control" width="60%"> 
	
	<div align="left"> 
	<select name="group"  onChange="submitMode('TEMP');" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
   	<%=newseat.getGroupCombo()%> 
	</select>
	</div>
		
	</td>
</tr>
  
<tr>   
		
      <td class="label" > 
        <div align="right"><font color='red'>*</font>Seat</div>
      </td>
      <td class="control" > 
      
   
	   <div align="left"> 
         <select name="seat" onChange="submitMode('TEMP');" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
            <%=newseat.getSeatCombo()%> 
          </select>
        </div>
	
	  </td>	  
    </tr>
    
 <tr>   
 <tr>		
			<td class="label"  width="40%"> 
				<div align="right"><font color='red'>*</font>Module</div>
			</td>
			<td class="control" width="60%"> 
				<div align="left"> 
					<select name="module"  onChange="submitMode('TEMP');" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
   					<%=newseat.getModuleCombo()%> 
					</select>
				</div>
			</td>
		</tr>
 <tr> 
		
      <td class="label" > 
        <div align="right"><font color='red'>*</font>DataSet Name</div>
      </td>
      <td class="control" > 
      
   
	 <div align="left"> 
         <select name="table"   onChange="submitModeTable('TEMP');" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
            <%=newseat.getTableCombo()%> 
          </select>
        </div>
	
	</td>	  
   </tr>
</table>
   				<!--  Code for creating the list boxes for the seats -->
   
   <table  class="formbg" width="97%" align="center" cellspacing="0" cellpadding="0">
   	<tr class="ShadedSubtitletagImage">	
           <td align="center" colspan="4" width='100%'><font color='red'>*</font>Data Set</td>
      </tr>
     <tr> 
		<td style="font-family: Verdana; height: 15px; background-color: #FFB468; font-weight:bold; font-style: normal;" colspan=4 align="right">
		<div id="divAtoZ">
			<a href='#' id="%Id" class="active" onclick="getLeftAndRightTableColumn('%');"><b>All</b></a>&nbsp;
			<a href='#' id="AId" class="link" onclick="getLeftAndRightTableColumn('A');"><b>A</b></a>&nbsp;
			<a href='#' id="BId" class="link" onclick="getLeftAndRightTableColumn('B');"><b>B</b></a>&nbsp;
			<a href='#' id="CId" class="link" onclick="getLeftAndRightTableColumn('C');"><b>C</b></a>&nbsp;
			<a href='#' id="DId" class="link" onclick="getLeftAndRightTableColumn('D');"><b>D</b></a>&nbsp;
			<a href='#' id="EId" class="link" onclick="getLeftAndRightTableColumn('E');"><b>E</b></a>&nbsp;
			<a href='#' id="FId" class="link" onclick="getLeftAndRightTableColumn('F');"><b>F</b></a>&nbsp;
			<a href='#' id="GId" class="link" onclick="getLeftAndRightTableColumn('G');"><b>G</b></a>&nbsp;
			<a href='#' id="HId" class="link" onclick="getLeftAndRightTableColumn('H');"><b>H</b></a>&nbsp;
			<a href='#' id="IId" class="link" onclick="getLeftAndRightTableColumn('I');"><b>I</b></a>&nbsp;
			<a href='#' id="JId" class="link" onclick="getLeftAndRightTableColumn('J');"><b>J</b></a>&nbsp;
			<a href='#' id="KId" class="link" onclick="getLeftAndRightTableColumn('K');"><b>K</b></a>&nbsp;
			<a href='#' id="LId" class="link" onclick="getLeftAndRightTableColumn('L');"><b>L</b></a>&nbsp;
			<a href='#' id="MId" class="link" onclick="getLeftAndRightTableColumn('M');"><b>M</b></a>&nbsp;
			<a href='#' id="NId" class="link" onclick="getLeftAndRightTableColumn('N');"><b>N</b></a>&nbsp;
			<a href='#' id="OId" class="link" onclick="getLeftAndRightTableColumn('O');"><b>O</b></a>&nbsp;
			<a href='#' id="PId" class="link" onclick="getLeftAndRightTableColumn('P');"><b>P</b></a>&nbsp;
			<a href='#' id="QId" class="link" onclick="getLeftAndRightTableColumn('Q');"><b>Q</b></a>&nbsp;
			<a href='#' id="RId" class="link" onclick="getLeftAndRightTableColumn('R');"><b>R</b></a>&nbsp;
			<a href='#' id="SId" class="link" onclick="getLeftAndRightTableColumn('S');"><b>S</b></a>&nbsp;
			<a href='#' id="TId" class="link" onclick="getLeftAndRightTableColumn('T');"><b>T</b></a>&nbsp;
			<a href='#' id="UId" class="link" onclick="getLeftAndRightTableColumn('U');"><b>U</b></a>&nbsp;
			<a href='#' id="VId" class="link" onclick="getLeftAndRightTableColumn('V');"><b>V</b></a>&nbsp;
			<a href='#' id="WId" class="link" onclick="getLeftAndRightTableColumn('W');"><b>W</b></a>&nbsp;
			<a href='#' id="XId" class="link" onclick="getLeftAndRightTableColumn('X');"><b>X</b></a>&nbsp;
			<a href='#' id="YId" class="link" onclick="getLeftAndRightTableColumn('Y');"><b>Y</b></a>&nbsp;
			<a href='#' id="ZId" class="link" onclick="getLeftAndRightTableColumn('Z');"><b>Z</b></a>&nbsp;
		</div>
		
		 </td>
	 </tr>
	
	 <tr>		
      <td class="control" width='20%'>         
      </td>
      <td class="control" width='20%'> 
        <div id="divLeftColumnId" align="left"> 
  
      <%=newseat.getColumnCombo() %> 
                   
         
        </div>
      </td>
	   <td class="control"  width='20%'> 
        <div align="left"> 
			       
			  <br>
              <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
              <br>
              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("1");'/> 	
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='checkBeforeMoveLeftSingle();'/> 	
               <br>
              <img src="../../images/backAll4.gif"   class="link"  onClick='checkBeforeMoveLeftAll();'/> 	
               
        </div>
      </td>
	  <td class="control"  width='20%'> 
          <div align="left"> 
      
          <%=newseat.getsecondColumnCombo()%>          
       
       
         </div>
      </td>
	  
    </tr>
	</table> 
	                         
	  	
   <table width="97%" cellspacing="0" cellpadding="0" align="center">
		<tr class="FOOTER">
			<td colspan=2>
		</tr>
	
		<tr>
			<td colspan=2>
				<div align="center" class="control_button2"> 
		<a 	style=cursor:hand class="button" tabindex=0 onClick='savePage(event,form1,"SAVE")' onkeyPress='savePage(event,form1,"SAVE")'><span class="save">Save</span></a> 
		<a 	style=cursor:hand class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="clear">Clear</span></a>
        <a 	style=cursor:hand class="button" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
       

        </div>
      </td></tr>
      <tr>
      <td>
      <div align="center"><font color="#FF3300" size='3'><%=newseat.getStatus()%></font></div>

    </tr>
    
  </table>
  
<input type="hidden" name="hmode">
<input type="hidden" name="seatName" value="<%=newseat.getSeatName()%>">
<%--<input type="hidden" name="seatId" value="<%=newseat.getSeatId()%>">--%>
<input type="hidden" name="module" 	value="<%=newseat.getModule()%>">
<input type="hidden" name="role" 	value="<%=newseat.getRole()%>">
<input type="hidden" name="table" 	value="<%=newseat.getTable()%>">
<input type="hidden" name="column" 	value="<%=newseat.getColumn()%>">
<input type="hidden" name="hospitalcode" value='<%=session.getAttribute("HOSPITAL_CODE")%>' />
<input type="hidden" name="seatId" value='<%=session.getAttribute("SEAT_ID")%>' />
<input type="hidden" name="selectedValues" 	value="">
<input type="hidden" name="selectedNames" 	value="">
<input type="hidden" name="strAlphbet" value="%"/>
<input type="hidden" name="strPrevAlphbet" />
<input type="hidden" name="tablelength" value="<%=newseat.getTablelength()%>">

 <anticsrf:csrftoken/>



    </FORM>
	</BODY>
</HTML>
   
