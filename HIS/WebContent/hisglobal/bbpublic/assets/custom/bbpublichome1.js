$(document).ready(function(){
// 	$(".increaseFont,.decreaseFont").click(function(){
// 		  var type=  $(this).val();
// 		   var curFontSize = $('.features-section').css('font-size');
// 		   alert(curFontSize);
// 		   if(type=='increase'){
// 		    $('.features-section').css('font-size', parseInt(curFontSize)+1);
// 		    }
// 		   else{
// 		    $('.features-section').css('font-size', parseInt(curFontSize)-1);
// 		   }
// 		   alert($('features-section').css('font-size'));

// 		     });	

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
	
	
	$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHSTATS&stattype=1",
			function (data1) {				
    $('#bbchart1').highcharts({
    	chart: {
            defaultSeriesType: 'spline',
            renderTo: 'container'
        },
        credits: {
        	enabled:false
        },
        title: {
            text: 'Nationwide Blood Donations: ' + new Date().getFullYear(),
            x: -20 //center
        },
        subtitle: {
            text: 'Source: eRaktkosh',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Number of Donations'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'A Positive',
            data: data1.ap 
        },
        {
            name: 'A Negative',
            data: data1.an 
        },
        {
            name: 'B Positive',
            data: data1.bp 
        },
        {
            name: 'B Negative',
            data: data1.an 
        },
        {
            name: 'AB Positive',
            data: data1.abp 
        },
        {
            name: 'AB Negative',
            data: data1.abn 
        },
        {
            name: 'O Positive',
            data: data1.op 
        },
        {
            name: 'O Negative',
            data: data1.on
        }
        
        
        ]
    });
    
	} );
    
	
  
    	$.getJSON("/BLDAHIMS/bloodbank/nearbyBB.cnt?hmode=FETCHSTATS&stattype=2",
    			function (data1) {
        $('#bbchart2').highcharts({
        	chart: {
                type: 'column'
            },
            credits: {
            	enabled:false
            },
            title: {
                text: 'Blood Banks with Critical Blood Stock'
            },
            subtitle: {
                text: 'Click the columns to view component wise details. Source: eRaktkosh.'
            },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                title: {
                    text: 'Number of Blood Banks'
                }

            },
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y:.0f}'
                    }
                }
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f}</b> blood banks<br/>'
            },

            series: [{
                name: 'Blood Groups',
                colorByPoint: true,
                data: [{
                    name: 'A Positive',
                    y: data1.ap,
                    drilldown: 'A Positive'
                }, {
                    name: 'A Negative',
                    y: data1.an,
                    drilldown: 'A Negative'
                }, {
                    name: 'B Positive',
                    y: data1.bp,
                    drilldown: 'B Positive'
                }, {
                    name: 'B Negative',
                    y: data1.bn,
                    drilldown: 'B Negative'
                }, {
                    name: 'AB Positive',
                    y: data1.abp,
                    drilldown: 'AB Positive'
                },
                {
                    name: 'AB Negative',
                    y: data1.abn,
                    drilldown: 'AB Negative'
                },
                {
                    name: 'O Positive',
                    y: data1.op,
                    drilldown: 'O Positive'
                },
                {
                    name: 'O Negative',
                    y: data1.on,
                    drilldown: 'O Negative'
                }
                
                ]
            }],
            drilldown: {
                series: [{
                    name: 'A Positive',
                    id: 'A Positive',
                    data: data1.apdrill
                }, {
                    name: 'A Negative',
                    id: 'A Negative',
                    data:data1.andrill
                }, {
                    name: 'B Positive',
                    id: 'B Positive',
                    data: data1.bpdrill
                }, {
                    name: 'B Negative',
                    id: 'B Negative',
                    data: data1.bndrill
                }, {
                    name: 'AB Positive',
                    id: 'AB Positive',
                    data: data1.abpdrill
                }, {
                    name: 'AB Negative',
                    id: 'AB Negative',
                    data: data1.abndrill
                }, {
                    name: 'O Positive',
                    id: 'O Positive',
                    data: data1.opdrill
                },{
                    name: 'O Negative',
                    id: 'O Negative',
                    data: data1.ondrill
                },]
            }
        });
    });	
	
});


$(function () {	
	
});