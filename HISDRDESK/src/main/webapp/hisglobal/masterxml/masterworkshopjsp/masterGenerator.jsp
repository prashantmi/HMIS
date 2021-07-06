<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<HTML>

<HEAD>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>Master Generator</TITLE>

	<link href="/HISClinical/hisglobal/css/master.css" type=TEXT/CSS rel=STYLESHEET>
	<link href="/HISClinical/hisglobal/css/Color.css" rel="stylesheet" type="text/css">
	<link href="/HISClinical/hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">

	<script>

function increment()
{
	var tableObj=document.getElementById('tab1');
	var numRows=tableObj.rows.length;
	
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	
	td1.innerHTML="<div align='right'>Column Header</div>"
	td1.className='tdfonthead';
	td1.colspan="1";
	td2.innerHTML="<input type='text' name='noOfColumn' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement();'>"
	td2.className='tdfont';
	td2.colspan="2";
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
						
}
function decrement()
{
	var Obj=document.getElementById("tab1");
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);
}

function showQueryDetail(temp)
{
	var rowObj=document.getElementById("id1");
	
	if(temp==1)
		rowObj.style.display='block';	
	else
		rowObj.style.display='none';	
}

function addQuery()
{
	var tableObj=document.getElementById('tab2');	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	td1.innerHTML="<div align='right'>Field:</div>";
	td1.className='tdfonthead';
	td1.colspan='3';
	td2.innerHTML="<input type='text' name='queryFields' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement1();'>";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
}

function decrement1()
{
	var Obj=document.getElementById("tab2");
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);
}

function addPrimary()
{
	var tableObj=document.getElementById('tab3');	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	td1.innerHTML="<div align='right'>Primary Field:</div>";
	td1.className='tdfonthead';
	td1.colspan='3';
	td2.innerHTML="<input type='text' name='queryFields' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement2();'>";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
}

function decrement2()
{
	var Obj=document.getElementById("tab3");
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);
}

function addTable()
{
	var tableObj=document.getElementById('tab4');	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	td1.innerHTML="<div align='right'>Alias:</div>";
	td1.className='tdfonthead';
	td1.colspan='3';
	td2.innerHTML="<input type='text' name='aliasTable' >";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);	
	
	td3.innerHTML="<div align='right'>Table:</div>";
	td3.className='tdfonthead';
	td3.colspan='3';
	td4.innerHTML="<input type='text' name='aliasTable' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement3();'>";
	td4.className='tdfont';
	td4.colspan='2';
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
}

function decrement3()
{
	var Obj=document.getElementById("tab4");
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);
}
function addClause()
{
	var tableObj=document.getElementById('tab5');	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	td1.innerHTML="<div align='right'>Clause:</div>";
	td1.className='tdfonthead';
	td1.colspan='2';
	td2.innerHTML="<input type='text' name='clause' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement4();'>";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);	
}

function decrement4()
{
	var Obj=document.getElementById("tab5");
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);	
}

function showCombo(temp)
{
	var Obj=document.getElementById("id2");
	
	if(temp==1)
	Obj.style.display='block';
	else
	Obj.style.display='none';
	var noOfControl=document.forms[0].lst_noofControls.value;
	
	tabObj=document.getElementById("tab6");
	var numRows=tabObj.rows.length;
	var td=new Array(noOfControl);	
	
	if(noOfControl>numRows)
	for(i=0;i<noOfControl;i++)
	{
		var tabRow=tabObj.insertRow(numRows);
		td[i]=document.createElement("TD");
		td[i].innerHTML='<div align="center">Combo'+i+' Details &nbsp;&nbsp;<img src="/HISClinical/hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="showComboDetails('+i+',1);"> '+
                        '  &nbsp;<img src="/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif" width="10" height="10" onClick="showComboDetails('+(i)+',2);"></div>'+
						'	 <div id="'+i+'" style="display:none">'+
						
						'	 <table width="100%" >	'+
						'	 <tr>	'+
						'	  	<td class="tdfonthead"  colspan="2"><div align="right">Index '+
                        '        &nbsp;</div></td> '+
						'		<td class="tdfont" colspan="2"><input  name="lst_index" type="text" size="20" maxlength="100"  >'+
						'	  </tr>'+
						'	  <tr>'+
						'	  	<td class="tdfonthead" colspan="2"><div align="right">Type '+
                        '       &nbsp;</div></td>'+
						'		<td class="tdfont" colspan="2"><input  name="lst_combo" type="text" size="20" maxlength="100" value="Combo" readonly>'+
						'	  </tr>'+
						'	  <tr>'+
						'	  	<td class="tdfonthead" colspan="2"><div align="right">Label&nbsp;</div></td>'+
						'		<td class="tdfont" colspan="2"><input  name="lst_label" type="text" size="20" maxlength="100"  >'+
						'	  </tr>'+
						'	  <tr>'+
						'	  	<td class="tdfonthead" colspan="2"><div align="right">Data&nbsp;</div></td>'+
								
                        '   <td class="tdfont" colspan="2"><input type="radio" name="radiobutton" value="1" onClick="showDynamic(dynamic'+i+',static'+i+',this);">'+
                        '      Dynamic&nbsp; '+
                        '      <input type="radio" name="radiobutton" value="2" onClick="showDynamic(static'+i+',dynamic'+i+',this);">'+
						'&nbsp;Static'+
                        '    </td>'+
                        '  </tr>'+
												
						'	<tr>'+
						'	<td class="tdfont" colspan="4">'+
						'  	<div id="dynamic'+i+'" style="display:none">'+
						'	<table width="100%" >'+
						'  		<tr>'+
						'		 <td class="tdfonthead" colspan="4"><div align="center">Options</div></td>'+
						'		</tr>'+
						'		<tr>'+
						'		 <td class="tdfonthead" colspan="2"><div align="right">TextField '+
                        '          &nbsp;</div></td>'+
						'		 <td class="tdfont" colspan="2"><input name="lst_textField" type="text" size="20" maxlength="100"></td>'+
						'		</tr>'+
								
						'		<tr>'+
						'		 <td class="tdfonthead" colspan="2"><div align="right">ValueField '+
                        '          &nbsp;</div></td>'+
						'		 <td class="tdfont" colspan="2"><input name="lst_valueField" type="text" size="20" maxlength="100"></td>'+
						'		</tr>'+
						'		<tr> '+
						'		 <td class="tdfonthead" colspan="4"><div align="center">Tables '+
                        '          &nbsp;<img src="/HISClinical/hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addComboTable(TAB'+i+');"></div></td>'+
						'		</tr>'+
						'		<tr>'+
						'		<td class="tdfont" colspan="4">'+
						'		<table width="100%" id="TAB'+i+'">'+
						'		</table>'+
						'		</td>'+
						'		</tr>'+
						
						'		<tr>'+
						'   	 <td class="tdfonthead" colspan="4"><div align="center">Condition:</div></td>'+
						'		</tr>'+
						'		<tr>'+
						'		 <td class="tdfonthead" colspan="1"><div align="right">General &nbsp;</div></td>'+
						'		 <td class="tdfont" colspan="2"><input name="lst_dynamic_general" type="text" size="20" maxlength="100"></td>'+
						'		</tr>'+
						'		<tr>'+
						'		 <td class="tdfonthead" colspan="1"><div align="right">Dependent &nbsp;</div></td>'+
						'		 <td class="tdfont" colspan="2"><input name="lst_dynamic_dependent" type="checkbox" >'+
                        '        &nbsp; <img src="/HISClinical/hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addComboClause(CLAUSE'+i+');"> '+
                        '      </td>'+
						'		</tr>'+
						' 		<tr>'+
						'		<td class="tdfont" colspan="4">	'+
						'		<table width="100%" id="CLAUSE'+i+'">'+
						'		</table>'+
						'		</tr>'	+	
						'		</td>'+
						'	</table>'+
						'	</div>'+
						'	</td>'+
						'	</tr>'+
						
						'	<tr>'+
						'	<td class="tdfont" colspan="4" >'+
						'	<div id="static'+i+'" style="display:none">'+						
						'	<table width="100%">'+
						'	<tr>'+
						'	  <td class="tdfonthead" colspan="4"> <div align="center">Options '+
                        '          &nbsp; <img src="/HISClinical/hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="showComboOption(option'+i+');" >	'+
                        '        </div></td>'+
						'	</tr>'+
						'	<tr>'+
						'	<td class="tdfont" colspan="4">'+
						'	<table width="100%" id="option'+i+'">'+
						'	</table>'+	
						'	</td>'+
						'	</tr>'+
						'	</table>'+
						'	</div>'+
						'	</tr>'+
						'	</td>'+
						
						' </table>'+
						' </div>';
		tabRow.appendChild(td[i]);
	}	
}

function showComboDetails(temp,e)
{
	var Obj=document.getElementById(temp);
	
	if(temp==Obj.id && e==1)
		Obj.style.display='block';
	else
		Obj.style.display='none';	
}

function showDynamic(temp,temp1,e)
{	
	var Obj1=document.getElementById(temp1.id);	
	var Obj=document.getElementById(temp.id);
	
	if(e.value=="2")
	{	
		Obj.style.display='block';
		Obj1.style.display='none';
	}	
	if(e.value=="1")
	{
		Obj.style.display='block';	
		Obj1.style.display='none';
	}	
}

function showComboOption(temp)
{
	var tableObj=document.getElementById(temp.id);	
	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	td1.innerHTML="<div align='right'>Value:</div>";
	td1.className='tdfonthead';
	td1.colspan='3';
	td2.innerHTML="<input type='text' name='aliasTable' >";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);	
	
	td3.innerHTML="<div align='right'>Text:</div>";
	td3.className='tdfonthead';
	td3.colspan='3';
	td4.innerHTML="<input type='text' name='aliasTable' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement7("+temp.id+");'>";
	td4.className='tdfont';
	td4.colspan='2';
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);	
}

function decrement7(temp)
{	
	var Obj=document.getElementById(temp.id);
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);	
}

function addComboTable(temp)
{	
	var tableObj=document.getElementById(temp.id);	
	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	td1.innerHTML="<div align='right'>Alias:</div>";
	td1.className='tdfonthead';
	td1.colspan='3';
	td2.innerHTML="<input type='text' name='aliasTable' >";
	td2.className='tdfont';
	td2.colspan='2';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);	
	
	td3.innerHTML="<div align='right'>Table:</div>";
	td3.className='tdfonthead';
	td3.colspan='3';
	td4.innerHTML="<input type='text' name='aliasTable' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement5("+temp.id+");'>";
	td4.className='tdfont';
	td4.colspan='2';
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
}

function decrement5(temp)
{	
	var Obj=document.getElementById(temp.id);
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);	
}

function addComboClause(temp)
{
	var tableObj=document.getElementById(temp.id);	
	var numRows=tableObj.rows.length;
	var tabRow=tableObj.insertRow(numRows);
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	td1.innerHTML="<div align='right'>Clause:</div>";
	td1.className='tdfonthead';
	td1.colspan='4';
	td2.innerHTML="<input type='text' name='clause' ><img src='/HISClinical/hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif' width='10' height='10' onClick='decrement6("+temp.id+");'>";
	td2.className='tdfont';
	td2.colspan='4';
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);	
}

function decrement6(temp)
{
	var Obj=document.getElementById(temp.id);
 	var noRows=Obj.rowslength;
 	var tabRow=Obj.deleteRow(noRows);	
}
	</script>
</HEAD>

<body>

<form name="masterGenerator" method="post">
	<%	int count = request.getParameter("count") == null || request.getParameter("count").equals("") ? 0 : Integer.parseInt(request.getParameter("count"));
		System.out.println("the count is:" + count);
	%>
	<table width="100%" border="0">
		<tr>
			<td width="68%" colspan=2>	
				<div align="center">
					<img src="/HISClinical/hisglobal/images/e_sushrut_header.gif" width="760" height="74">
				</div>
			</td>
		</tr>
		
		<tr>
			<td height="19" valign="bottom">	
				<div align="left">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif"></font>
				</div>
			</td>
			
			<td height="19" valign="bottom">
				<div align="right">
					<a href="/HISClinical/startup/cnt_login.jsp?loginMode=LOGOUT&uid=E050000100">
						<strong>
							<font size="2">
								<font color="#9900CC">
									Logout&nbsp;
								</font>
							</font>
						</strong>
					</a>
				</div>
			</td>
		</tr>	
	</table>

	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TR>
			<TD class=DIVIDER height=1>
				<IMG height=1 src="/HISClinical/hisglobal/images/tp.gif" width=650 border=0>
			</TD>
		</TR>
		<TR>
			<TD vAlign=top align=left>
				<TABLE cellSpacing=0 cellPadding=10 width="100%" border=0>
					<tr>		
						<TD>
							<table cellspacing=0 cellpadding=0 width="97%" border=0>
								<tr>
									<td valign=bottom align="left" colspan=3>
										<table cellspacing=0 cellpadding=0 width="1056%" border=0>
											<tr>
												<td width=1 height=1>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
												
												<td width="10" height=1 class=DIVIDER>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
											</tr>

											<tr>
												<td class=DIVIDER>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
												
												<td id="cid" class=SELECTEDTAB noWrap height=21>
													&nbsp;
												</td>
											</tr>					
										</table>
									</td>
									
									<td valign=bottom align="left" colspan=3>
										<table cellspacing=0 cellpadding=0 width="100%" border=0>
											<tr>
												<td width=1 height=1>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
												
												<td width="269" height=1 class=DIVIDER>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
											</tr>
											
											<tr>
												<td class=DIVIDER>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
												<td id='cid' class=TAB style="cursor: pointer" nowrap height=20 onClick="return submitHeaderForm('COMP',1);"
													onKeyPress="return submitHeaderForm(event,'COMP',1)" onMouseOver="setColor('1');"
													onMouseOut="resetColor('1');">
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
													<a> &nbsp;Common Values</a>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
													<b></b>
												</td>
												
												<td width="10" class=DIVIDER>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
											</tr>
											<tr>
												<td class=DIVIDER width=1 height=1>
													<img height=1 src="/HISClinical/hisglobal/images/tp.gif" width=1 border=0>
												</td>
												<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
											</tr>
					
										</table>
										</td>
						<td valign=bottom align=BASELINE colspan=3>
						<table cellspacing=0 cellpadding=0 width="100%" border=0>
	
							<tr>
								<td width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td width="269" height=1 class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td id='cid' class=TAB style="cursor: pointer" nowrap tabindex=1 height=20
									onClick="return submitHeaderForm('CASE',2);" onKeyPress="return submitHeaderForm(event,'CASE',2)"
									onMouseOver="setColor('2');" onMouseOut="resetColor('2');" onFocus="setColor('2');" onBlur="resetColor('2');"><img
									height=1 src="../hisglobal/images/tp.gif" width=1 border=0><a> &nbsp;List Page&nbsp; </a><img height=8
									src="../hisglobal/images/tp.gif" width=8 border=0><b></b></td>
								<td width="10" class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
	
						</table>
						</td>
						<td valign=bottom align=BASELINE colspan=3>
						<table cellspacing=0 cellpadding=0 width="100%" border=0>
	
							<tr>
								<td width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td width="269" height=1 class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td id='cid' class=TAB style="cursor: pointer" nowrap tabindex=1 height=20
									onClick="return submitHeaderForm('EXAM',3);" onKeyPress="return submitHeaderForm(event,'EXAM',3)"
									onMouseOver="setColor('3');" onMouseOut="resetColor('3');" onFocus="setColor('3');" onBlur="resetColor('3');"><img
									height=1 src="../hisglobal/images/tp.gif" width=1 border=0><a> &nbsp;Add Page</a><img height=1
									src="../hisglobal/images/tp.gif" width=1 border=0><b></b></td>
								<td width="10" class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
	
						</table>
						</td>
						<td valign=bottom align=BASELINE colspan=3>
						<table cellspacing=0 cellpadding=0 width="260%" border=0>
	
							<tr>
								<td width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td width="89" height=1 class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td id='cid' class=TAB style="cursor: pointer" nowrap tabindex=1 height=20
									onClick="return submitHeaderForm('INVE',4);" onKeyPress="return submitHeaderForm(event,'INVE',4)"
									onMouseOver="setColor('4');" onMouseOut="resetColor('4');" onFocus="setColor('4');" onBlur="resetColor('4');"><img
									height=1 src="../hisglobal/images/tp.gif" width=1 border=0><a> &nbsp;Modify Page</a><img height=1
									src="../hisglobal/images/tp.gif" width=1 border=0><b></b></td>
								<td width="75" class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
								<td class=DIVIDER width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
	
	
						</table>
						</td>
						<td valign=bottom align=BASELINE colspan=3>&nbsp;</td>
						<td valign=bottom align=BASELINE colspan=3>&nbsp;</td>
						<td valign=bottom align=BASELINE colspan=3>&nbsp;</td>
						<td valign=bottom align=BASELINE colspan=3>
						<table cellspacing=0 cellpadding=0 width="100%" border=0>
	
							<tr>
								<td width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td width="269" height=1 class=DIVIDER><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
							<tr>
	
							</tr>
							<tr>
								<td class=DIVIDER width=1 height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
								<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
	
						</table>
						</td>
						<td valign=bottom align=BASELINE colspan=3>&nbsp;</td>
						<td valign=bottom width="76%">
						<table cellspacing=0 cellpadding=0 width="100%" border=0>
							<tr>
								<td class=DIVIDER height=1><img height=1 src="../hisglobal/images/tp.gif" width=1 border=0></td>
							</tr>
						</table>
						</td>
					</tr>
	
				</table>
	
				<table cellspacing=0 cellpadding=0 width="100%" border=0>
					<tr>
						<td class=DIVIDER width=1 rowspan=5><img height=1 src="/hisglobal/images/tp.gif" width=1 border=0></td>
						<td width=10 rowspan=5><img height=1 src="/hisglobal/images/tp.gif" idth=10 border=0></td>
						<td height=10 width="1140"><img height=1 src="/hisglobal/images/tp.gif" width=1 border=0></td>
						<td width=11 rowspan=5><img height=1 src="/hisglobal/images/tp.gif" width=1 border=0></td>
					</tr>
					<tr>
	
						<td width="1140">
	
	
						<table cellspacing=1 cellpadding=1 width="100%" border=0>
	
							<tr>
								<td colspan=4 class=header><strong>Common Values</strong></td>
							</tr>
							<tr>
								<td width="51%" class=tdfonthead>
								<div align="right"><font size="2">Name : </font></div>
								</td>
								<td colspan="2" class=tdfont><font size="2"> <input name="name" type="text" size="20" maxlength="100">
								</font></td>
							</tr>
							<tr>
								<td width="51%" class=tdfonthead>
								<div align="right"><font size="2">Master Name<b>:&nbsp;</b> </font></div>
								</td>
								<td colspan="2" class=tdfont>
								<div align="left"><input name="masterName" type="text" size="20" maxlength="100"></div>
								<div align="right"></div>
								</td>
							</tr>
							<tr>
								<td width="51%" class=tdfonthead nowrap>
								<div align="right"><font size="2">Master Title:</font></div>
								</td>
								<td width="48%" class=tdfont>
								<div align="right"><font size="2">
								<div align="left"><input name="masterTitle" type="text" size="20" maxlength="100"></div>
								</td>
	
							</tr>
	
						</table>
	
						</td>
					</tr>
					<tr>
						<td width="1140">
	
						<TABLE cellSpacing=1 cellPadding=0 width="100%" border=0>
							<tr>
								<td colspan="4" class=header><strong>List Page</strong></td>
							</tr>
							<tr>
	
								<td width="51%" class=tdfonthead>
								<div align="right"><font size="2">Table Heading : </font></div>
								</td>
	
								<td colspan="2" class="tdfont"><input name="lst_tableHeading" type="text" size="20" maxlength="100"></td>
							</tr>
	
							<tr>
	
								<td width="51%" class=tdfonthead>
								<div align="right"><font size="2">Primary Table </font><font size="2">: </font></div>
								</td>
	
								<td colspan="2" class="tdfont"><input name="lst_primaryTable" type="text" size="20" maxlength="100"></td>
							</tr>
							<tr>
	
								<td width="51%" class=tdfonthead>
								<div align="right"><font size="2">No Of Column Header&nbsp; </font></div>
								</td>
	
								<td colspan="2" class="tdfont">&nbsp; <img src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10"
									height="10" onClick="increment();"> &nbsp; <img src="../../hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif"
									width="10" height="10" onClick="decrement();"></td>
							</tr>
							<tr>
								<td class='tdfont' colspan='4'>
	
								<table width='100%' id='tab1'>
	
								</table>
	
								</td>
							</tr>
	
							<tr>
								<td colspan="4" class=tdfonthead>
								<div align="center"><font size="2">Query Detail &nbsp;&nbsp; <img
									src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="showQueryDetail('1');">
								&nbsp;&nbsp; <img src="../../hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif" width="10" height="10"
									onClick="showQueryDetail('2');"> </font></div>
								</td>
							</tr>
						</table>
						<div id="id1" style="display: none">
						<table width='100%'>
							<tr>
								<td class="tdfonthead" colspan="4">
								<div align="center"><label class="tablehead">Query Fields &nbsp;</label> &nbsp;<img
									src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addQuery();"></div>
								</td>
							</tr>
							<tr>
								<td class='tdfont' colspan='4'>
								<table width='100%' id='tab2'>
	
								</table>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="4">
								<div align="center"><label class="tablehead">Primary Fields &nbsp;</label> &nbsp;<img
									src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addPrimary();"></div>
								</td>
							</tr>
							<tr>
								<td class='tdfont' colspan='4'>
								<table width='100%' id='tab3'>
	
								</table>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="4">
								<div align="center"><label class="tablehead">Table</label> &nbsp;<img
									src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addTable();"></div>
								</td>
							</tr>
							<tr>
								<td class='tdfont' colspan='4'>
								<table width='100%' id='tab4'>
								</table>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="4">
								<div align="center"><label class="tablehead">Condition : &nbsp;</label></div>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="1">
								<div align="right">General : &nbsp;</div>
								</td>
								<td class="tdfont" colspan="2"><input name="lst_conGeneral" type="text" size="20" maxlength="100"></td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="1">
								<div align="right">Dependent &nbsp;</div>
								</td>
								<td class="tdfont" colspan="2"><input name="lst_Depend" type="checkbox" value=""> &nbsp;<img
									src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10" onClick="addClause();"></td>
							</tr>
							<tr>
								<td class='tdfont' colspan='4'>
								<table width='100%' id='tab5'>
								</table>
								</td>
							</tr>
	
							<tr>
								<td class="tdfonthead" colspan="1">
								<div align="right">isValidField &nbsp;</div>
								</td>
	
								<td class="tdfont" colspan="2">
								<div align="left"><input name="lst_isvalidField" type="text" size="20" maxlength="100"></div>
								</td>
	
							</tr>
						</table>
						</div>
						<table width='100%'>
							<tr>
	
								<td class="tdfonthead" colspan="1">
								<div align="right">No Of Controls &nbsp;</div>
								</td>
								<td class="tdfont" colspan="2"><input name="lst_noofControls" type="text" size="20" maxlength="100">
								&nbsp;&nbsp; <img src="../../hisglobal/images/PT_PORTAL_IC_PLUS_ENG_1.gif" width="10" height="10"
									onClick="showCombo('1');"> &nbsp; <img src="../../hisglobal/images/PT_PORTAL_IC_MINUS_ENG_1.gif" width="10"
									height="10" onClick="showCombo('2');"></td>
							</tr>
						</table>
						<div id="id2" style="display: none">
						<table width='100%'>
							<tr>
	
								<td class="tdfont" colspan="4">
								<table width='100%' id='tab6'>
								</table>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" colspan="1">
								<div align="right">Default &nbsp;</div>
								</td>
								<td class="tdfont" colspan="2"><input name="lst_default" type="text" size="20" maxlength="100"></td>
							</tr>
							<tr>
								<td width="100%" colspan=5 class=header>&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>