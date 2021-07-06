
<!DOCTYPE html>

<html lang="en">

<head>

  <title>Vital</title>

  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <style>
	label{
		margin-top:7px;
	}
	.alignRight{
		text-align:right;
	}
	.alignCenter{
		text-align:center;
	}
	.alignLeftPaddingLeftZero{
		text-align:left;
		padding-left:0;
	}
	.paddingRightZero{
		padding-right:0;
	}
	.paddingLeftRightZero{
		padding-right:0;
		padding-left:0;
	}
	.errorMessage{
		font-style:italic;
		color:red;
	}
  </style>
  
  <script>
  	var count=0;
  	$(document).ready(function(){

  		$('.allergiesAddRows').click(function(){
  			count++;
  			var allergyName=$(this).parent().parent().find('input[name="allergyName"]').val();
  			var siteName=$(this).parent().parent().find('select[name="site"] option:selected').text();
  			var duration=$(this).parent().parent().find('input[name="noOfDays"]').val();
  			var durationCombo=$(this).parent().parent().find('select[name="duration"] option:selected').text();
  			var otherInfo=$(this).parent().parent().find('input[name="otherInfo"]').val();
			//alert(allergyName+"  "+siteName+"  "+duration+"   "+durationCombo+"   "+otherInfo);
			if(allergyName.trim()!='' && siteName.trim()!='' && duration.trim()!='' && durationCombo.trim()!='' && otherInfo.trim()!=''){
				str='<div class="row"  id="rowId'+count+'">'+
				'<div class="form-group col-md-3 alignLeftPaddingLeftZero">'+
					'<input type="text" class="form-control" value="'+allergyName+'" name="allergyName" readonly id="allergyNameId'+count+'">'+
				'</div>'+
				'<div class="form-group col-md-2 alignLeftPaddingLeftZero">'+
					'<input type="text" class="form-control" value="'+siteName+'" name="site" readonly id="siteId'+count+'">'+
				'</div>'+
				'<div class="form-group col-md-4 alignLeftPaddingLeftZero">'+
					'<div class="col-md-6">'+
						'<input type="text" class="form-control" value="'+duration+'" name="noOfDays" readonly id="noOfDaysId'+count+'">'+
					'</div>'+
					'<div class="col-md-6 alignLeftPaddingLeftZero">'+
						'<input type="text" class="form-control" value="'+durationCombo+'" name="duration" readonly id="durationId'+count+'">'+
					'</select>'+
					'</div>'+
				'</div>'+
				'<div class="form-group col-md-2 alignLeftPaddingLeftZero">'+
					'<input type="text" class="form-control" value="'+otherInfo+'" name="otherInfo" readonly id="otherInfoId'+count+'">'+
				'</div>'+
				'<div class="form-group col-md-1">'+
					'<button class="btn btn-sm btn-danger allergiesRemoveRow" onclick="$(this).parent().parent().remove();" id="removeBtnId'+count+'">Remove</button>'+
				'</div>'+
				'</div>';
				$(this).closest('.addAllergyNewRowClass').append(str);
				$(this).parent().parent().find('input[name="allergyName"]').val('');
  				$(this).parent().parent().find('input[name="noOfDays"]').val('');
  				$(this).parent().parent().find('input[name="otherInfo"]').val('');
			}
			else{
				alert("Please fill all the fields.");
			}
			
		});

		/*$('.allergiesRemoveRow').click(function(){
			alert("yruyh");
			$(this).parent().parent().remove();
		});*/

		$("#heightId").keypress(function (e) {
	     	var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#heightId").css("border-color","red");
	     		$("#heightId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#heightErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#heightId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}

			$("#heightId").css("border-color","");
	     	$("#heightId").css("box-shadow", "");
	     	var weight=$("#weightId").val();
	     	var height=$("#heightId").val();

	     	if(parseFloat(height) > 250.00){
	     		$("#heightErrmsg").html("Height cannot exceed 250cms.").show();
	     		$("#heightId").val('');
	     	}
	     	if(parseFloat(height) == 0){
	     		$("#heightErrmsg").html("Height cannot be 0.").show();
	     		$("#heightId").val('');
	     	}
	     	else{
	     		$("#heightErrmsg").html("");
	     	}

	     	if(weight!='' && height!=''){
	     		var temp=parseFloat(parseFloat(height)/100);
	     		var bmi=parseFloat(weight)/(temp*temp);

	     		if(bmi > 30){
	     			$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Obese").show();
					$('input[name="bmiErrMsgTxt"]').val('Obese');
			        return false;
				}
				else if(bmi < 18.5 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Underweight.").show();
					$('input[name="bmiErrMsgTxt"]').val('Underweight');
			        return false;
				}
				else if(bmi >= 18.5 && bmi <= 24.9 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Normal").show();
					$('input[name="bmiErrMsgTxt"]').val('Normal');
			        return false;
				}
				else if(bmi >= 25.0 && bmi <= 29.9 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Overweight").show();
					$('input[name="bmiErrMsgTxt"]').val('Overweight');
			        return false;
				}
				else{
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("");
				}
	     	}
		});

		$("#weightId").keypress(function (e) {
	     	var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#weightId").css("border-color","red");
	     		$("#weightId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#weightErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#weightId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			$("#weightId").css("border-color","");
	     	$("#weightId").css("box-shadow", "");

	     	var weight=$("#weightId").val();
	     	var height=$("#heightId").val();

	     	if(parseFloat(weight) > 200.00){
	     		$("#weightErrmsg").html("Weight cannot exceed 200kgs.").show();
	     		$("#weightId").val('');
	     	}
	     	if(parseFloat(weight) ==0){
	     		$("#weightErrmsg").html("Weight cannot be 0.").show();
	     		$("#weightId").val('');
	     	}
	     	else{
	     		$("#weightErrmsg").html("");
	     	}

	     	if(weight!='' && height!=''){
	     		var temp=parseFloat(parseFloat(height)/100);
	     		var bmi=parseFloat(weight)/(temp*temp);

	     		if(bmi > 30 ){
	     			$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Obese").show();
					$('input[name="bmiErrMsgTxt"]').val('Obese');
			        return false;
				}
				else if(bmi < 18.5 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Underweight.").show();
					$('input[name="bmiErrMsgTxt"]').val('Underweight');
			        return false;
				}
				else if(bmi >= 18.5 && bmi <= 24.9 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Normal").show();
					$('input[name="bmiErrMsgTxt"]').val('Normal');
			        return false;
				}
				else if(bmi >= 25.0 && bmi <= 29.9 ){
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("Overweight").show();
					$('input[name="bmiErrMsgTxt"]').val('Overweight');
			        return false;
				}
				else{
					$("#bmiId").val(bmi.toFixed(2));
					$("#bmiErrmsg").html("");
				}
	     	}
		});
		
		$("#respRateId").keypress(function (e) {
	     	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	     		$("#respRateId").css("border-color","red");
	     		$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#respRateErrmsg").html("Digits Only").show().fadeOut("slow");
		        return false;
	    	}
	    	else{
	    		$("#respRateId").css("border-color","");
	     		$("#respRateId").css("box-shadow", "");
	     		return true;
	    	}
	    });
		$("#respRateId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp=$("#respRateId").val();
			
			if(temp > 0 && temp < 9){
				//$("#respRateId").css("border-color","red");
	     		//$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#respRateErrmsg").html("Low Respiratory Rate").show();
		        $('input[name="respRateErrMsgTxt"]').val('Low Respiratory Rate');
		        return false;
			}
			else if(temp > 20){
				//$("#respRateId").css("border-color","red");
	     		//$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#respRateErrmsg").html("High Respiratory Rate").show();
		        $('input[name="respRateErrMsgTxt"]').val('High Respiratory Rate');
		        return false;
			}
			else if(parseFloat(temp)== 0){
	     		$("#respRateErrmsg").html("Respiratory Rate cannot be 0.").show();
	     		$("#respRateId").val('');
	     	}
			else{
				$("#respRateId").css("border-color","");
	     		$("#respRateId").css("box-shadow", "");
	     		$("#respRateErrmsg").html("");
	     		$('input[name="respRateErrMsgTxt"]').val('Normal');
	     		return true;
			}
		});

		$("#bmiId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57)){
		    	$("#bmiId").css("border-color","red");
	     		$("#bmiId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#bmiErrmsg").html("Values Not Allowed. Enter weight and height values to calculate BMI.").show().fadeOut("slow");
		        return false;
		   	}
	    	else{
	    		$("#bmiId").css("border-color","");
	     		$("#bmiId").css("box-shadow", "");
	     		return true;
	    	}
	    });
		$("#bmiId").keyup(function(){
			$("#bmiId").css("border-color","");
	     	$("#bmiId").css("box-shadow", "");
		});
		
		$("#haemoglobinId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#haemoglobinId").css("border-color","red");
	     		$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#haemoglobinErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#haemoglobinId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			window.patGender = $('#patGender').text().charAt(0).toUpperCase();
			var temp = $("#haemoglobinId").val();

			if(window.patGender == 'F'){
				if(parseFloat(temp)==0){	
			        $("#haemoglobinErrmsg").html("Heamoglobin cannot be 0.").show();
			        $("#haemoglobinId").val('');
				}
				else if(parseFloat(temp) > 0 && parseFloat(temp) < 12.0){
					//$("#haemoglobinId").css("border-color","red");
		     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#haemoglobinErrmsg").html("Low Heamoglobin").show();
			        $('input[name="haemoglobinErrMsgTxt"]').val('Low Heamoglobin');
			        return false;
				}
				else if(parseFloat(temp) > 15.5){
					//$("#haemoglobinId").css("border-color","red");
		     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#haemoglobinErrmsg").html("High Heamoglobin").show();
			        $('input[name="haemoglobinErrMsgTxt"]').val('High Heamoglobin');
			        return false;
				}
				else{
					$("#haemoglobinId").css("border-color","");
	     			$("#haemoglobinId").css("box-shadow", "");
	     			$("#haemoglobinErrmsg").html("");
	     			$('input[name="haemoglobinErrMsgTxt"]').val('Normal');
	     			return true;
				}
			}
			else if(window.patGender == 'M'){
				if(parseFloat(temp)==0){	
			        $("#haemoglobinErrmsg").html("Heamoglobin cannot be 0.").show();
			        $("#haemoglobinId").val('');
				}
				else if(parseFloat(temp) < 13.5){
					//$("#haemoglobinId").css("border-color","red");
		     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#haemoglobinErrmsg").html("Low Heamoglobin").show();
			        $('input[name="haemoglobinErrMsgTxt"]').val('Low Heamoglobin');
			        return false;
				}
				else if(parseFloat(temp) > 17.5){
					//$("#haemoglobinId").css("border-color","red");
		     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#haemoglobinErrmsg").html("High Heamoglobin").show();
			        $('input[name="haemoglobinErrMsgTxt"]').val('High Heamoglobin');
			        return false;
				}
				else{
					$("#haemoglobinId").css("border-color","");
	     			$("#haemoglobinId").css("box-shadow", "");
	     			$("#haemoglobinErrmsg").html("");
	     			$('input[name="haemoglobinErrMsgTxt"]').val('Normal');
	     			return true;
				}
			}
		});
		
		$("#temperatureId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#temperatureId").css("border-color","red");
	     		$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
		        $("#temperatureErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }
		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#temperatureId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			if(parseFloat($("#temperatureId").val())==0){	
		        $("#temperatureErrmsg").html("Temperature cannot be 0.").show();
		        $("#temperatureId").val('');
			}
			else if(parseFloat($("#temperatureId").val())< 99.5 ){	
		        $("#temperatureErrmsg").html("Normal Temperature").show();
		        $("#temperatureErrmsg").val('');
		        $('input[name="temperatureErrMsgTxt"]').val('Normal Temperature');
			}
			else if(parseFloat($("#temperatureId").val()) >= 99.5 &&  parseFloat($("#temperatureId").val()) < 100.9){
    			//$("#temperatureId").css("border-color","red");
     			//$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        	$("#temperatureErrmsg").html("Fever").show();
	        	$('input[name="temperatureErrMsgTxt"]').val('Normal Temperature');
    		}
    		else if(parseFloat($("#temperatureId").val()) >= 100.9){
    			//$("#temperatureId").css("border-color","red");
     			//$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        	$("#temperatureErrmsg").html("High Fever").show();
	        	$('input[name="temperatureErrMsgTxt"]').val('High Fever');
    		}
    		else{
				$("#temperatureId").css("border-color","");
		     	$("#temperatureId").css("box-shadow", "");
		     	$("#temperatureErrmsg").html("");
		     	$('input[name="temperatureErrMsgTxt"]').val('Normal');
		     	return true;
		    }
		});
		
		$("#diastolicId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#diastolicId").css("border-color","red");
	     		$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#diastolicId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp=parseFloat($("#diastolicId").val());
			var x=$("#systolicId").val();
			var temp1;
			if(x == ''){
				temp1 = NaN;
			}
			else{
				temp1=parseFloat(x);
			}
			if(temp != NaN && temp1 != NaN){
				if((temp <= 60) && (temp1 <= 90)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Low BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Low BP');
			        return false;
				}
				if((temp <= 60) && (temp1 > 90)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Ideal BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
			        return false;
				}
				else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Ideal BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
			        return false;
				}
				else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 1").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
			        return false;
				}
				else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 1").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
			        return false;
				}
				else if((temp > 80 && temp <= 90) && temp1 > 140){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 2").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
			        return false;
				}
				else if(temp > 90 && temp1 > 140){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 2").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
			        return false;
				}
				else if(temp == 0){
					$("#bpErrmsg").html("Diastolic cannot be 0.").show();
					$("#diastolicId").val('');
				}
				else{
					$("#diastolicId").css("border-color","");
			     	$("#diastolicId").css("box-shadow", "");
			     	$("#bpErrmsg").html("");
			     	$('input[name="bpErrMsgTxt"]').val('Normal');
			     	return true;
				}
			}				
		});

		$("#systolicId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#systolicId").css("border-color","red");
	     		$("#systolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#systolicId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp1=parseFloat($("#systolicId").val());
			var x=$("#diastolicId").val();
			var temp;
			if(x == ''){
				temp = NaN;
			}
			else{
				temp=parseFloat(x);
			}
			if(temp != NaN && temp1 != NaN){
				if((temp <= 60) && (temp1 <= 90)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Low BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Low BP');
			        return false;
				}
				if((temp <= 60) && (temp1 > 90)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Ideal BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
			        return false;
				}
				else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Ideal BP").show();
			        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
			        return false;
				}
				else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 1").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
			        return false;
				}
				else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 1").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
			        return false;
				}
				else if((temp > 80 && temp <= 90) && temp1 > 140){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 2").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
			        return false;
				}
				else if(temp > 90 && temp1 > 140){
					//$("#diastolicId").css("border-color","red");
		     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
			        $("#bpErrmsg").html("Hypertension Stage 2").show();
			        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
			        return false;
				}
				else if(temp1==0){	
			        $("#bpErrmsg").html("Systolic cannot be 0.").show();
			        $("#systolicId").val('');
				}
				else{
					$("#diastolicId").css("border-color","");
			     	$("#diastolicId").css("box-shadow", "");
			     	$("#bpErrmsg").html("");
			     	$('input[name="bpErrMsgTxt"]').val('Normal');
			     	return true;
				}
			}
		});

		$("#fastingId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#fastingId").css("border-color","red");
	     		$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#fastingErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#fastingId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp=parseFloat($("#fastingId").val());

			if(temp==0){	
		        $("#fastingErrmsg").html("Blood Sugar Fasting cannot be 0.").show();
		        $("#fastingErrmsg").val('');
		        $("#fastingId").val('');
			}
			else if(temp < 70){
				//$("#fastingId").css("border-color","red");
	     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#fastingErrmsg").html("Low Sugar").show();
		        $('input[name="fastingErrMsgTxt"]').val('Low Sugar');
		        return false;
			}
			else if(temp >= 101 && temp < 125){
				//$("#fastingId").css("border-color","red");
	     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#fastingErrmsg").html("Pre Diabetes").show();
		        $('input[name="fastingErrMsgTxt"]').val('Pre Diabetes');
		        return false;
			}
			else if(temp >= 125){
				//$("#fastingId").css("border-color","red");
	     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#fastingErrmsg").html("High Diabetes").show();
		        $('input[name="fastingErrMsgTxt"]').val('High Diabetes');
		        return false;
			} 
			else{
				$("#fastingId").css("border-color","");
	     		$("#fastingId").css("box-shadow", "");
	     		$("#fastingErrmsg").html("");
	     		$('input[name="fastingErrMsgTxt"]').val('Normal');
	     		return true;
			}
		});

		$("#ppRateId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#ppRateId").css("border-color","red");
	     		$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#ppRateErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#ppRateId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp=parseFloat($("#ppRateId").val());

			if(temp==0){	
		        $("#ppRateErrmsg").html("PP Blood Sugar Value cannot be 0.").show();
		        $("#ppRateErrmsg").val('');
		        $("#ppRateId").val('');
			}
			else if(temp < 70){
				//$("#ppRateId").css("border-color","red");
	     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#ppRateErrmsg").html("Low Sugar").show();
		        $('input[name="ppRateErrMsgTxt"]').val('Low Sugar');
		        return false;
			}
			else if(temp >= 141 && temp < 200){
				//$("#ppRateId").css("border-color","red");
	     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#ppRateErrmsg").html("Pre Diabetes").show();
		        $('input[name="ppRateErrMsgTxt"]').val('Pre Diabetes');
		        return false;
			}
			else if(temp >= 200){
				//$("#ppRateId").css("border-color","red");
	     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#ppRateErrmsg").html("High Diabetes").show();
		        $('input[name="ppRateErrMsgTxt"]').val('High Diabetes');
		        return false;
			} 
			else{
				$("#ppRateId").css("border-color","");
	     		$("#ppRateId").css("box-shadow", "");
	     		$("#ppRateErrmsg").html("");
	     		$('input[name="ppRateErrMsgTxt"]').val('Normal');
	     		return true;
			}
		});

		$("#hba1cId").keypress(function (e) {
			var self = $(this);
		   	self.val(self.val().replace(/[^0-9\.]/g, ''));
		   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
			       (e.which != 0 && e.which != 8)){
	     		$("#hba1cId").css("border-color","red");
	     		$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#hba1cErrmsg").html("Numbers Only").show().fadeOut("slow");
		        return false;
	    	}
	    	var text = $(this).val();
		    if ((e.which == 46) && (text.indexOf('.') == -1)) {
		        setTimeout(function() {
		            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
		                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
		            }
		        }, 1);
		    }

		    if ((text.indexOf('.') != -1) &&
		        (text.substring(text.indexOf('.')).length > 2) &&
		        (e.which != 0 && e.which != 8) &&
		        ($(this)[0].selectionStart >= text.length - 2)) {
		            event.preventDefault();
		    }
	    });
		$("#hba1cId").keyup(function(){
			if($(this).val().replace(/^(0+)/g, '')){
				$(this).val($(this).val().replace(/^(0+)/g, ''));
			}
			var temp=parseFloat($("#hba1cId").val());

			if(temp==0){	
		        $("#hba1cErrmsg").html("HBA1C Value cannot be 0.").show();
		        $("#hba1cErrmsg").val('');
		        $("#hba1cId").val('');
			}
			else if(temp >= 5.7 && temp <= 6.4){
				//$("#hba1cId").css("border-color","red");
	     		//$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#hba1cErrmsg").html("Pre Diabetes").show();
		        $('input[name="hba1cErrMsgTxt"]').val('Pre Diabetes');
		        return false;
			}
			else if(temp >= 6.5){
				//$("#hba1cId").css("border-color","red");
	     		//$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#hba1cErrmsg").html("High Diabetes").show();
		        $('input[name="hba1cErrMsgTxt"]').val('High Diabetes');
		        return false;
			} 
			else{
				$("#hba1cId").css("border-color","");
	     		$("#hba1cId").css("box-shadow", "");
	     		$("#hba1cErrmsg").html("");
	     		$('input[name="hba1cErrMsgTxt"]').val('Normal');
	     		return true;
			}
		});

		$('#weightId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#heightId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#haemoglobinId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#diastolicId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#systolicId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#fastingId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#ppRateId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#hba1cId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});
		$('#temperatureId').bind("paste", function(e) {
			var text = e.originalEvent.clipboardData.getData('Text');
			if ($.isNumeric(text)) {
			    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
			        e.preventDefault();
			        $(this).val(text.substring(0, text.indexOf('.') + 3));
			   }
			}
			else {
			        e.preventDefault();
			     }
		});

		$('#haemoglobin').tooltip({
            placement:"top"
        });
        $('#respRate').tooltip({
            placement:"top"
        });
        $('#VitalsPrintBtn').tooltip({
            placement:"top"
        });
        $('#bloodPressure').tooltip({
            placement:"top"
        });
        $('#hba1c').tooltip({
            placement:"top"
        });
        $('#ppRate').tooltip({
            placement:"top"
        });
        $('#fasting').tooltip({
            placement:"top"
        });

        $("#VitalsPrintBtn").on('click',function(){
			$('.close').hide();
			$('#VitalsPrintBtn').hide();
			$('#saveBtn').hide();
			$('.opdBayHeaderTwo').hide();
			$('[data-toggle="tooltip"]').tooltip("hide");
			window.print();
			$('.close').show();
			$('#VitalsPrintBtn').show();
			$('#saveBtn').show();
			$('.opdBayHeaderTwo').show();
			return false;
		});

		$(window).bind("resize", function () {
		 	if($(window).width() < 992){
			  $('#bloodSugarDivId').removeClass('alignRight');
			}
		}).trigger('resize');

		$(window).bind("resize", function () {
		 	if($(window).width() >= 992){
			  $('#bloodSugarDivId').addClass('alignRight');
			  $('#bloodPressureDivId').addClass('alignRight');
			}
		}).trigger('resize');

  	});
  </script>

  	<style>
  		@media only screen and (max-width: 991px)  {
		  /*.form-control {
		    background-color: lightblue;
		  }*/
		}
		.tooltip{
		    position:absolute;
		    z-index:1020;
		    display:block;
		    visibility:visible;
		    padding:5px;
		    font-size:13px;
		    opacity:0;
		    filter:alpha(opacity=0)
		}
  	</style>
</head>

<body>

â€‹

	<div class="container">

	  	<h2 class="opdBayHeaderTwo">OPD BAY</h2>
		
		
		<div class="modal-content">       
				<div class="modal-body" style="text-align: left;"> 
					<div class="row" style="min-height:400px;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">Ã—</button>
							<h2 class="modal-title text-left">Vitals & General Examination</h2>
							<div class="row">
								<div class="col-md-5 col-sm-3 col-xs-1">
								</div>
								<div class="col-md-7 col-sm-9 col-xs-11">
									<b style="color:#7d849b; font-weight:400;"><span id="patName">Patient Name</span> / <span id="crNo">CR Number</span> / <span id="patAge">Age</span> / <span id="patGender">Male</span></b>
								</div>
								<div class="col-md-1 col-sm-1 col-xs-1 alignRight">
									<i class="fa fa-print" id="VitalsPrintBtn" data-toggle="tooltip" title="Print Vitals"></i>
								</div>
							</div>
						</div>	
						
						&nbsp;
							  
						<!-- <label for="patient detail">Patient Name / CR Number </label> -->
						 <div class="row">
							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="weight">Weight:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" maxvalue="200" id="weightId" placeholder="kgs" name="weight">&nbsp;<span id="weightErrmsg" class="errorMessage"></span>
								</div>
							</div>
						
							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="height">Height:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="heightId" placeholder="cms" name="height" >&nbsp;<span id="heightErrmsg" class="errorMessage"></span>
								</div>
							</div>

							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="bmi">BMI:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="bmiId" placeholder="kg/m2" name="bmi" readonly>&nbsp;<span id="bmiErrmsg" class="errorMessage"></span>
								</div>
							</div>

							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="temperature">Temp(F):</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="temperatureId" placeholder="In Fahrenheit" name="temperature">
									&nbsp;<span id="temperatureErrmsg" class="errorMessage"></span>
								</div>
							</div>

						</div>	

				<div class="row">
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="respRate" id="respRate" data-toggle="tooltip" title="Respiration Rate">Resp. Rate:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="respRateId" placeholder="breaths/min" name="respRate">&nbsp;<span id="respRateErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="haemoglobin" id="haemoglobin" data-toggle="tooltip" title="Haemoglobin">Hgb:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="haemoglobinId" placeholder="g/dL" name="haemoglobin">&nbsp;<span id="haemoglobinErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-3 col-sm-2 col-xs-3 alignRight" id="bloodPressureDivId">
							<label for="diastolic" id="bloodPressure" data-toggle="tooltip" title="Blood Pressure">BP(mmHg):</label>
						</div>
						<div class="col-md-5 col-sm-5 col-xs-5 alignLeftPaddingLeftZero">
							<div class="col-md-10 col-sm-10 col-xs-10 paddingLeftRightZero">
								<input type="text" class="form-control" id="diastolicId" placeholder="Diastolic" name="diastolic"><!-- &nbsp;<span id="diastolicErrmsg" class="errorMessage"></span> -->
							</div>
							<div class="col-md-2 col-sm-2 col-xs-2 paddingRightZero" style="font-size:20px;">
								<p><b>/</b></p>
							</div>
						</div>
						<!-- <div class="col-md-1 paddingLeftRightZero">
						  <label for="separator">/</label>
						</div> -->
						<div class="col-md-4 col-sm-5 col-xs-4 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="systolicId" placeholder="Systolic" name="systolic">&nbsp;<span id="bpErrmsg" class="errorMessage"></span>
						</div>
					</div>
				</div>

				<div class="row">
					
			    </div>

				<div class="row">
					<div class="form-group col-md-3 col-sm-12 col-xs-12">
						<div class="col-md-12 col-sm-12 col-xs-12 alignRight" id="bloodSugarDivId">
							<label for="bloodSugar" id="bloodSugarId" data-toggle="tooltip" title="Blood Sugar">B.S. Levels(mg/dl)</label>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="fasting" id="fasting" data-toggle="tooltip" title="Blood Sugar Fast">Fast:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="fastingId" placeholder="Fasting" name="fasting">&nbsp;<span id="fastingErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="ppRate" id="ppRate" data-toggle="tooltip" title="Blood Sugar PP Rate">PP:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="ppRateId" placeholder="PP" name="ppRate">&nbsp;<span id="ppRateErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="hba1c" id="hba1c" data-toggle="tooltip" title="Blood Sugar HBA1C Rate">HBA1C:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="hba1cId" placeholder="%" name="hba1c">&nbsp;<span id="hba1cErrmsg" class="errorMessage"></span>
						</div>
					</div>
									
					<!-- <div class="form-group col-md-4">
						<div class="col-md-5 alignRight">
							<label for="fasting">Fasting (mg/dl):</label>
						</div>
						<div class="col-md-7 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="fastingId" placeholder="Blood Fasting Sugar" name="fasting">&nbsp;<span id="fastingErrmsg"></span>
						</div>
					</div>

					<div class="form-group col-md-4">
						<div class="col-md-5 alignRight">
							<label for="ppRate">PP(mg/dl):</label>
						</div>
						<div class="col-md-7 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="ppRateId" placeholder="Blood Sugar PP Rate" name="ppRate">&nbsp;<span id="ppRateErrmsg"></span>
						</div>
					</div>

					<div class="form-group col-md-4">
						<div class="col-md-5 alignRight">
							<label for="hba1c">HBA1C (%):</label>
						</div>
						<div class="col-md-7 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="hba1cId" placeholder="Blood Sugar HBA1C Rate" name="hba1c">&nbsp;<span id="hba1cErrmsg"></span>
						</div>
					</div> -->
				</div>

				<div class="row">
					<div class="form-group col-md-12 col-sm-12 col-xs-12">
						<div class="col-md-2 col-sm-3 col-xs-3 alignRight">
							<label for="symptoms">Symptoms/Info:</label>
						</div>
						<div class="col-md-10 col-sm-9 col-xs-9 alignLeftPaddingLeftZero">
							<textarea class="form-control" id="symptomsId" name="symptoms" maxlength="2000"></textarea>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
					</div>
					<div class="col-md-4 alignCenter">
						<button class="btn btn-success saveBtn" id="saveBtn" type="submit"><i class="fa fa-save"></i> Save</button>
					</div>
					<div class="col-md-4">
					</div>
				</div>


				<input type="hidden" name="respRateErrMsgTxt" value=''> 
				<input type="hidden" name="bmiErrMsgTxt" value=''> 
				<input type="hidden" name="temperatureErrMsgTxt" value=''>
				<input type="hidden" name="diastolicErrMsgTxt" value=''>
				<input type="hidden" name="bpErrMsgTxt" value=''>
				<input type="hidden" name="systolicErrMsgTxt" value=''>
				<input type="hidden" name="fastingErrMsgTxt" value=''>
				<input type="hidden" name="haemoglobinErrMsgTxt" value=''>
				<input type="hidden" name="ppRateErrMsgTxt" value=''>
				<input type="hidden" name="hba1cErrMsgTxt" value=''>

	</div>

</body>

</html>

€‹

