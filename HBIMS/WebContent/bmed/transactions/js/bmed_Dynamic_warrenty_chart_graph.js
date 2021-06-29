$(document).ready(function () {
	var data = [];
	var data1 = [];
	var dataset = [];
	var ticks = [];
	var max = 0;
	var tickSize = 1;
	var Bar1 = [];
	var Bar2 = [];
	var pie1 = [];
	var pie2 = [];
	

	var equipment = document.getElementById("Equipment").value.split("@");
	var bar1 = document.getElementById("Bar1").value.split("@");
	var bar2 = document.getElementById("Bar2").value.split("@");
	var barLabel1 = document.getElementById("Bar1_Label").value;
	var barLabel2 = document.getElementById("Bar2_Label").value;	


			for(j=0;j<bar1.length-1;j++)
			{
				var temp = bar1[j];
				if(temp >= max)
					max = temp;
			}
			if(max >= 10)
			{
				tickSize = 2;
			}
			
			for(j=0;j < equipment.length-1;j++)
			{
				Bar1[j] = [j,bar1[j+1]];
				Bar2[j] = [j,bar2[j+1]];							
				ticks[j] = [j,equipment[j+1]];				
				pie1[j] = { label:  equipment[j+1], data: bar1[j+1] }
				pie2[j] = { label:  equipment[j+1], data: bar2[j+1] }
			}
		
		dataset = [
					  {
						 label: barLabel1,
						 data : Bar1,
						 numbers:
							{
								show:true,
								yAlign:10,
								xAlign:10
							},
						 bars:
						 {
						 	show: true,
                			barWidth: 0.1,
                			order: 1,
						 numbers:
							{
								show:true,
								yAlign:10,
								xAlign:10
							},
 						 },
					  },
					  {
						label: barLabel2,
						 data : Bar2,
						 bars:
						 {
						 	show: true,
                			barWidth: 0.1,
                			order: 1,          
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
	  

	  