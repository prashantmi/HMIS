
<div class='div-table'>
	<div class='div-table-row '>
		<div class='div-table-col   width20'>
			<font color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif">*</font><b><font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif">CR Number </font></b>
		</div>
		<div class='div-table-col   width20'>
			<font color="#000000" size="1em"
				face="Verdana, Arial, Helvetica, sans-serif"> <input
				type="text" name="patCrNo" maxlength="13" tabindex="1" size="15"
				value="null" onkeydown="setPrevValue(this, event);"
				onkeypress="if(event.keyCode!=13) return validateNumericOnly(event); else return submitFormOnValidate(validateCRNoCHECK('13'),'GETPATDTL')' ">
			</font>
		</div>
		<div class='div-table-col   width20'>
			<a href='#' class='button' onclick='alert(' go');returnsubmitFormOnValidate(validateCRNoCHECK("13"),"GETPATDTL")'><span
				class='cancel'>Go</span></a>
		</div>
		<div class='div-table-col   width20'>
			<a href='#' class='button'
				onclick="openPopup('/AHIMS/registration/searchByNamePopup.cnt',event,350,750)"><span
				class='cancel'>Search</span></a>
		</div>