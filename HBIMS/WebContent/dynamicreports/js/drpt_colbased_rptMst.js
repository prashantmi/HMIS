	function validateInsert() {

		var hisValidator = new HISValidator("rptparamBean");

		hisValidator.addValidation("strColumnDisplayName", "req",
				"Display Name is a Mandatory Field");
		 
		hisValidator.addValidation("strColumnFormula", "req",
				"Formula is a Mandatory Field");
	 
		if(document.forms[0].strReportHeaderParamReq.value == 1){
			
			hisValidator.addValidation("strReportHeaderParamId", "dontselect=0",
			"Please Select an Header Parameter Mapping");
					
		}
		

		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();

		var mode = "COLBASESAVE";
	 				
		if (retVal) {
			 		 
			document.forms[0].hmode.value = mode;
			document.forms[0].submit();
		} else {
			return false;
		}
	}
	
	 
	function createMultiRow(){
		
		
		multiRow('multirow1',new Array( 
				'<input name="strColumnDisplayName"  type="text" class="txtFldMax" maxlength="250"  onkeypress="return validateData(event,4);" >',
				'<input name="strColumnPrefix"  type="text" class="txtFldNormal" maxlength="100"  onkeypress="return validateDataWithSpecialChars(event,18,\""."\");" >',
				'<input name="strColumnFormula"  type="text" class="txtFldMax" maxlength="250"  onkeypress="return validateDataWithSpecialChars(event,8 , \""+-*/.()"\");" >',
				'<input name="strColumnSuffix"  type="text" class="txtFldNormal" maxlength="100"  onkeypress="return validateDataWithSpecialChars(event,18,\""."\");" >' ,
				'<select name="strOutColumnAlign" class="comboNormal" ><option selected="selected" value="left">Left</option><option value="center">Center</option><option value="right">Right</option></select>'), 1, 1, true);
	
	}
	