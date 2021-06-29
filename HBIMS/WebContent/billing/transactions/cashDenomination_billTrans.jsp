<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cash Denomination</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<style type="text/css">
	input:focus::-webkit-input-placeholder { color:transparent; }
	input:focus:-moz-placeholder { color:transparent; } /* FF 4-18 */
	input:focus::-moz-placeholder { color:transparent; } /* FF 19+ */
</style>
</head>
<body>
<div class="PopupDiv" id="CashDetailsDiv" style="display: block; width: 90%; position: absolute; visibility: visible; z-index: 106;">
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="60%">
			<tbody>
			<tr class="HEADER">
			<td colspan="2">
				<img src="../../hisglobal/images/stop.png" title="('Click to Close this Window')"  onclick="Popup.hide('CashDetailsDiv'),totalvalue(event);" onkeypress="Popup.hide('CashDetailsDiv')" align="right">Details of Currency</td>
			</tr>
			<tr>
				<td class="LABEL"  width="50%"><div align="right"><b>1000 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfThousandNotes" maxlength="10" size="6" placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 1000 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfThousandNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>500 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfFiveHundNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 500 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfFiveHundNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>100 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp; 
					<input name="intNoOfHundredNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 100 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfHundredNotes" type="text"> 
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>50 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfFiftyNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 50 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfFiftyNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>20 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfTwentyNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 20 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfTwentyNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>10 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfTenNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 10 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfTenNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>5 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfFiveNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 5 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfFiveNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>2 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfTwoNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 2 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfTwoNotes" type="text">
				</td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>1 ×</b></div></td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfOneNotes" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of 1 Notes')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfOneNotes" type="text">
			    </td>
			</tr>
			<tr height="25">
				<td class="LABEL" align="center"  width="50%">
				<div align="right"><b>Coin</b>&nbsp;&nbsp;&nbsp;&nbsp;				
					<select name="intWhichCoin"  onclick="makingEmptyFieldForCash(this)" title="('Enter the Coin Value')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intWhichCoin"><option value="0" selected="selected">0</option>
						<option value="50">50</option>
						<option value="25">25</option>
						<option value="20">20</option>
						<option value="10">10</option>
						<option value="5">5</option></select>&nbsp;<b>×</b>
				</div>
				</td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intNoOfCoin" maxlength="10" size="6"  placeholder="0" onclick="makingEmptyFieldForCash(this)" title="('Enter number of Coins')"  onkeyup="sum()" onkeypress="return validateData(event,5);" onblur="checkFieldForCash(this)" id="intNoOfCoin" type="text">
			    </td>
			</tr>
			<tr height="30">
				<td class="LABEL" align="center"  width="50%">
					<div align="right"><b>Total</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</td>
				<td class="CONTROL" width="50%">&nbsp;
					<input name="intTotalForCashDiv" maxlength="13" size="15" tabindex="0" value="0" title="('Total')"  readonly="readonly" id="intTotalForCashDiv" type="text">
			    </td>
			</tr>
			
			<tr height="30">
				<td class="LABEL" colspan="2" align="center"  width="100%">
					<div align="center">
						<img src="../../hisglobal/images/Go.png"  onclick="Popup.hide('CashDetailsDiv'),totalvalue(event);" title="('Click to Close this Window and Total will be reflected into Amount to be Deposited Field')"  onkeypress="Popup.hide('CashDetailsDiv'),totalvalue(event);">
					</div>
				</td>
			</tr>			
			<tr class="addheader">
				<td class="HEADER" colspan="2" height="19"></td>
			</tr>
			</tbody></table>
			</div>
</body>
</html>