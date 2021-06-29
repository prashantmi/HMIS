/*$(document).load(function(){
	$('.loadingSppiner').hide();
});*/


$(document).ready(function(){

	 $(".unavailable [data-toggle='modal']").click(function(){
	     let bedName =  $(this).children('p').text();
	     let patDeptName = $(this).attr('patDeptName');
	     let patName = $(this).attr('patName');
	     //alert(patName);
	     let crNo = $(this).attr('crNo');
	     let addDate = $(this).attr('addDate');
	     $('h4.modal-title b').text(bedName);
	     $('#patDeptName').text(patDeptName);   
	     $('#patName').text(patName);      
	     $('#crNo').text(crNo);      
	     $('#addDate').text(addDate);      
	     });
	 $(".available [data-toggle='modal']").click(function(){
	     let bedName =  $(this).children('p').text();   
	     $('h4.modal-title b').text(bedName); 
	     });
    $(".loadingSppiner").fadeOut(2000, function() {
                });


  $('canvas').click(function(){
    	let refPanel = $(this).parent().attr('href1');
    	$(refPanel).slideDown();
        $('.panel').not(refPanel).slideUp();
   });

});
	function removeColapse(e){
         $(e).parent().children('.panel-body').slideUp();
         $(e).attr('onclick',"addColapse(this,event)");
         
	}
function addColapse(e){
         $(e).parent().children('.panel-body').slideDown();
         $(e).attr('onclick',"removeColapse(this,event)");
        /* $(this).removeClass('addColapse');
         $(this).addClass('colapse');*/
	}

function showAvailable(e){
	let vl = $(e).val();
    
    if(vl==2)
    	$('.unavailable').slideUp();
    else
    	$('.unavailable').slideDown();
}

function showWardChoice(e){
	let vl = $(e).val();
    
    if(vl==1)
    {
     $('#vip').slideDown();
     $('.panel').not('#vip').slideUp();
    }	
    else if(vl==2)
    {
     $('#private').slideDown();
     $('.panel').not('#private').slideUp();
    }
    else if(vl==3)
    {
     $('#newPrivate').slideDown();
     $('.panel').not('#newPrivate').slideUp();
    }
    else if(vl==4)
    {
     $('#general').slideDown();
     $('.panel').not('#general').slideUp();
    }
    else if(vl==5)
    {
     $('#female').slideDown();
     $('.panel').not('#female').slideUp();
    }
    else
    	$('.panel').slideDown();
}


/*popup toast*/
$(".popupToast").hover(function () {
	  var popup=$(this).find(".popuptextToast")[0];
	  popup.classList.toggle("show");
	 /* 
	  * for dynamic arrow --check later
	  * 
	  * if(popup.offsetWidth>100)
		  	popup.style.marginLeft=-(popup.offsetWidth/2);*/
	});

/* end popup toast*/