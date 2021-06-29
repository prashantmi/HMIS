/**
 * Developer : Singaravelan
 * Date		 : Oct-2015
 * USAGE	 : For Writing textBox and textArea text on tooltip having class "tooltipClass".
 */

//To Show the Tool Tip in the Visit Reason, Added by Singaravelan on 20-Oct-2015
$(document).ready(function(){
	$('.tooltipClass').qtip({
	    content:
	    {
	    	text: function(event,api) {
	    		var content = $(this).val();
	    		return content === '' ? "Max 100 Characters" : content;
	    	}          
	    },				          
	    style: {  	
	    	classes: 'tipCustomTextAreaStyle qtip-rounded'
	    },
	    position: {		       
	        my: 'bottom center', 
	        at: 'top center', 
	    },
	    show: {
            event: 'focus'
        },
        hide: {
           event: 'blur'
        }
	});
	
	$('.tooltipClass').keypress(function(event){
		$(this).qtip('option', 'content.text',$(this).val());
	});
	
	$('.tooltipClass').keyup(function(event){
		if($(this).val()!="")
			$(this).qtip('option', 'content.text',$(this).val());
		else
			$(this).qtip('option', 'content.text',"Max 100 Characters");	
	});
	
	$('.tooltipClass').focus(function(event){
		$(this).qtip('option', 'content.text',$(this).val());		
	});
	
	/*
Start : Surabhi
Date : 9th Nov 16
Reason : to add qtip class in mlc detail process
*/
	$('.tooltipClass1').qtip({
	    content:
	    {
	    	text: function(event,api) {
	    		var content = $(this).val();
	    		return content === '' ? "MLC Details Modification cannot be done if final opinion is entered" : content;
	    	}          
	    },				          
	    style: {  	
	    	classes: 'tipCustomTextAreaStyle qtip-rounded'
	    },
	    position: {		       
	        my: 'bottom center', 
	        at: 'top center', 
	    },
	    show: {
            event: 'focus'
        },
        hide: {
           event: 'blur'
        }
	});
	
	$('.tooltipClass1').keypress(function(event){
		$(this).qtip('option', 'content.text',$(this).val());
	});
	
	$('.tooltipClass1').keyup(function(event){
		if($(this).val()!="")
			$(this).qtip('option', 'content.text',$(this).val());
		else
			$(this).qtip('option', 'content.text',"MLC Details Modification cannot be done if final opinion is entered");	
	});
	
	$('.tooltipClass1').focus(function(event){
		$(this).qtip('option', 'content.text',$(this).val());		
	});
	//End
});
