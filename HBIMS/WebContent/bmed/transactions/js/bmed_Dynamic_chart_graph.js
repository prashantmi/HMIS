$(document).ready(function () {
	
	var data = [];
	var dataset = [];
	var ticks = [];
	var max = 0;
	var tickSize = 1;

	var equipment = document.getElementById("Equipment").value.split("@");
	var stock = document.getElementById("Stock").value.split("@");
	
		
		for(j=0;j < equipment.length-1;j++)
		{
		data[j] = [j,stock[j+1]];
		ticks[j] = [j,equipment[j+1]];					
		}
		for(j=0;j<stock.length;j++)
			{
				var temp = stock[j];
				if(temp >= max)
				{
					max = temp;
					if(max >= 10)
					{
						tickSize = 2;
						break;
					}
				}
			}
			var data1 = [];
		for(j=0;j<equipment.length-1;j++)
		{
			data1[j] = { label:  equipment[j+1], data: stock[j+1] }
		}	
	var dataset = [{ label: "Inventory-Equipment/Lab", data: data, color: "#808080" }];
	options = {
            series: {
                bars: {
                    show: true,
                    numbers:{
                    	show:true,
                    	yAlign:1,
                    	xAlign:1,
                    	
                    }
                }
            },
            bars: {
                align: "center",
                barWidth: 0.5
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
                labelBoxBorderColor: "#A9A9A9",                
                position: "ne"
            },
            grid: {
                hoverable: false,
                borderWidth: 1
            }
            
            };				 
            $.plot($("#barPlaceHolder"), dataset,options);	
            
            $( "#pieChart" ).click(function() {
				$("#barPlaceHolder").unbind();	
					$("#yAxixLabel").css("visibility","hidden");					

				$.plot('#barPlaceHolder', data1, {
									    series: {
									        pie: {
									            show: true,
									            radius: 3/4,
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
									});
									
			});
            
            $( "#barChart" ).click(function() {
				$("#barPlaceHolder").unbind();	
					$("#yAxixLabel").css("visibility","visible");					

				$.plot($("#barPlaceHolder"), dataset,options);

			});
         
         
         function labelFormatter(label, series) {
			return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + series.data[0][1] + "</div>";
			}     
            					
  });