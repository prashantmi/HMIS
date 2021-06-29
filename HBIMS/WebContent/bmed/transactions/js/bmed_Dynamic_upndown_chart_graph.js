$(document).ready(function () {
	var data = [];
	var data1 = [];
	var dataset = [];
	var ticks = [];
	var up = [];
	var down = [];
	var max = 0;
	var tickSize = 1;
	var pie1 = [];
	var pie2 = [];

	var equipment = document.getElementById("Equipment").value.split("@");
	var Up = document.getElementById("Up").value.split("@");
	var Down = document.getElementById("Down").value.split("@");
	
			for(j=0;j<Up.length-1;j++)
			{
				var temp = up[j];
				if(temp => max)
					max = temp;
			}
			if(max >= 10)
			{
				tickSize = 2;
			}
			
			for(j=0;j < equipment.length-1;j++)
			{
				up[j] = [j,Up[j+1]];
				down[j] = [j,Down[j+1]];							
				ticks[j] = [j,equipment[j+1]];	
				pie1[j] = { label:  equipment[j+1], data: Up[j+1] }
				pie2[j] = { label:  equipment[j+1], data: Down[j+1] }
							
			}
		
		dataset = [
					  {
						 label: "Up",
						 data : up,
						 bars:
						 {
						 	show: true,
                			barWidth: 0.5,
                			order: 1,
                			numbers:{
		                    	show:true,
		                    	yAlign:2,
		                    	xAlign:2,		                    	
		                    }          
						 },
					  },
					  {
						label: "Down",
						 data : down,
						 bars:
						 {
						 	show: true,
                			barWidth: 0.5,
                			order: 1,
                			numbers:{
		                    	show:true,
		                    	yAlign:1,
		                    	xAlign:1,		                    	
		                    }          
						 },
					  }					
		];
var pieOption = {
						series: {
							pie: {
								 show: true,
								 radius: 1,
								 label: {
									    show: true,
									    radius: 3/4,
									    formatter: labelFormatter,
									    background: {
									                 opacity: 0.5,
									                 color: '#000'
									                }
									      }
								   }
							      },
						 legend: {
							  show: false
								 }
					};
		
/* Bar Chart*/  
 $( "#barChart" ).click(function() {
				$("#piePlaceHolder").unbind();	
				$("#yAxixLabel").css("visibility","visible");					
				$("#barDiv").css("display","block");
				$("#pieDiv").css("display","none");	
    $.plot($("#barPlaceHolder"), dataset,{
        bars : {
				show : true,
				showNumbers: true,
			   }, 
        xaxis: {            	
            	autoscaleMargin: 0.5,
                axisLabel: "Inventory-Equipment",
                tickLength: 0,
                axisLabelUseCanvas: false,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 10,
                ticks: ticks
            },
            yaxis: {
            	minTickSize:0,
            	tickSize:tickSize,
            	tickDecimals: 0,            	
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 3
            },
            legend: {
                noColumns: 0,
                labelBoxBorderColor: "none",
                position: "ne"
            },
            grid: {
                hoverable: true,
                borderWidth: 2,
            },
        	series: {
            	shadowSize: 1
        	}
        });
    }); 
/* *******************Pie Chart************************** */
        
         $( "#pieChart" ).click(function() {
				$("#barPlaceHolder").unbind();	
				$("#yAxixLabel").css("visibility","hidden");					
				$.plot('#piePlaceHolder', pie1, pieOption );
				$.plot('#piePlaceHolder1', pie2, pieOption);
				$("#barDiv").css("display","none");
				$("#pieDiv").css("display","block");
									
			});
/* **************************Default Function ********************** */
  $( "#barChart" ).click();	 
/* *********************LabelFormatter Function ************************** */
         function labelFormatter(label, series) {
			return "<div  style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + series.data[0][1] + "</div>";
			}     			        
 
	  });