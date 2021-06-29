$(document).ready(function(){	
	//var currZoom = 100;

	$.ajax({
	    url: "/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHNOTIFICATION&type=1",
	    success: function(data) {	    	
	    	 $('#js-news').append(data);
	    	 $('#js-news').ticker();
	    }
	  });
	
	$.ajax({
	    url: "/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHNOTIFICATION&type=2",
	    success: function(data) {	    		    	
	    	 $('#notices').append(data);
	    	 $(".various").fancybox({
	 			
	 			maxWidth	: 800,
	 			maxHeight	: 600,
	 			fitToView	: false,
	 			width		: '70%',
	 			height		: '70%',
	 			autoSize	: false,
	 			closeClick	: false,
	 			openEffect	: 'none',
	 			closeEffect	: 'none',
	 			helpers: {
	 			    overlay: {
	 			      locked: false
	 			    }
	 			  }
	 		});// fancybox
	    }
	  });	

	// var  realsize= 100; //parseInt($("div").css('font-size')); 
	// var curSize = realsize;
	 
	 
		//$('#incfont').click(function(){	
		    							//curSize = curSize + 1;
		  // curSize= parseInt($("#ee").css('font-size')) +2;
		    							//var size = curSize + "%" ;
		    							//alert(size);
		    							//$("*").css("font-size",size);
//     curSize= parseInt($("dd").css('font-size')) + 2;                
////		if(curSize<=40)
//     $("div").css('font-size', curSize);
			//document.body.style.zoom="115%"	
			  //var step = 5;
           // currZoom += step;
         //   $('body').css('zoom', ' ' + currZoom + '%');
						
    //  });                                                                  
		
	//	$('#decfont').click(function(){	   
//     curSize= parseInt($("#dd").css('font-size')) - 2;                 
//		if(curSize>=8)
//     $("div").css('font-size', curSize);
			        // $("*").css("font-size", 10); 
			//document.body.style.zoom="90%"
	
			 // var step = 5;
          //currZoom -= step;
         // $('body').css('zoom', ' ' + currZoom + '%');
							
   //  }); 
		
	//   $('#reset').click(function(){  
	      //$("*").css("font-size", 14);   
		  // document.body.style.zoom="100%"
	 //  });
	   
	   //increment and decrement
//	var  realsize= parseInt($("div").css('font-size')); 
//	 
//	$('#incfont').click(function(){		
//    curSize= parseInt($("div").css('font-size')) + 2;                
//	if(curSize<=40)
//    $("div").css('font-size', curSize);
//     });                                                                  
//	
//	$('#decfont').click(function(){	   
//    curSize= parseInt($("div").css('font-size')) - 2;                 
//	if(curSize>=4)
//    $("div").css('font-size', curSize);
//    }); 
//	
//   $('#reset').click(function(){  
//      $("div").css("font-size", realsize);                                
//   });
	   
	   
	   
	
	
	//**************************************************************************//
	var transformZoom = 1;
	  var cssZoom = 100;

	  
	  
	  $('.make_greater').click(function() {
	  //alert('hello');
	    var step;

	    if (Modernizr.testProp('zoom') === true) {
		
	      step = 5;
	      cssZoom += step;
	      $('body').css('zoom', ' ' + cssZoom + '%');
	    } else {
	      console.log('no css zoom');
	      step = 0.05;
	      transformZoom += step;
		  
	      $('html').css('MozTransform', 'scale(' + transformZoom + ',' + transformZoom + ')');
	      $('html').css('transform-origin', 'top center');
	    }
	  });
	  
	  
	  

	  $('.make_smaller').click(function() {
	    var step;
	    //only firefox create problem
	    if (Modernizr.testProp('zoom') === true) {
	      console.log('CSS zoom supported');

	      step = 5;
	      cssZoom -= step;
	      $('body').css('zoom', ' ' + cssZoom + '%');
	    } else {
	      console.log('no css zoom');
	      step = 0.05;
	      transformZoom -= step;
	      $('html').css('MozTransform', 'scale(' + transformZoom + ',' + transformZoom + ')');
		console.log('no css zoom 1');
	      $('html').css('transform-origin', 'top center');
		console.log('no css zoom 2');
	    }
	  });
	  
	  
	  $('#reset').click(function(){  
	      //$("*").css("font-size", 14);   
		  // document.body.style.zoom="100%"
		  cssZoom = 100;
		 if (Modernizr.testProp('zoom') === true) {
		   $('body').css('zoom', ' ' + 100 + '%');
		   }
		  else 
		  {
		      console.log('no css zoom');
		     transformZoom = 1;
		     $('html').css('MozTransform', 'scale(' + 1 + ',' + 1 + ')');
		      $('html').css('transform-origin', 'top center');
		    }
		 console.log('cssZoom');
	  });
	  
	  //************************************************************************//
	
	
	
	
	
	
	
	
	   //dark and light
	   $("#dark").click(function(){ 
		 
	   		   $("section").toggleClass("darkbg1");
	   		   $("header").toggleClass("darkbg1");
	   		   $("ul").toggleClass("darkbg1");
	   		   $("li").toggleClass("darkbg1");
	   		   $("a").toggleClass("darkbg2");
	   	    	$("hr").toggleClass("darkbg2");
	   		
	   		$(".toggle1").toggleClass("darkbg1");
	   		$(".toggle2").toggleClass("darkbg2");
		});

		
	
});


$(function () {	
	
});