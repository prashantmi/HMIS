<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<html>
<head>
<meta charset=utf-8>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bed Dashboard</title>
	<link rel="stylesheet" type="text/css" href="/HBIMS/ipd/bedstatusdash/css/style.css">
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../css/all.min.css"> 
    <link href="../css/newlayout.css" rel="stylesheet" type="text/css"> 
	 <!-- Latest compiled and minified CSS -->
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!-- <link href='https://fonts.googleapis.com/css?family=Orbitron' rel='stylesheet' type='text/css'> -->	
<!-- family=Orbitron ----- font included in css part of bed Images -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>    -->
 
 


	
<style type='text/css'>

.bedbutton{
border          : 3px #B5B5B5 outset;
padding         : 25 20px;
color           : black;
cursor          : pointer ;
cursor          : hand;
text-align      : center;
text-decoration : none;
font            : normal 13px Verdana;
}

.bedImages{
font-family: 'Orbitron', sans-serif;
margin-left:22%;
height: 50px;
width: 80px;
cursor: pointer;
vertical-align: middle;

}
.bedImagesFocus{
height: 50px;
width: 90px;
cursor: pointer;
vertical-align: middle;
font-family: 'Orbitron', sans-serif;

}
.bedImagesSmall{
height: 20px;
width: 50px;
cursor: pointer;
vertical-align: middle;
font-family: 'Orbitron', sans-serif;

}
.aval
{
color: #004573;
font-weight: 700;
display: inline-block;
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 9px;
}
.bedbutton:visited, .bedbutton:hover, .bedbutton:active{
color: grey;
}
.panel-body{
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-weight: 620;
font-size:10px;
color: #004573;

}
b{
font-weight: 1000;
color:black;
}
.occupDetail
{
font-size:9px;
font-weight: 750;
font-family: "Helvetica Neue",Helvetica,Arial,monospace;

}
.occup{
font-size:10px;
font-family: "Helvetica Neue",Helvetica,Arial,monospace;
color:#C02404;
}
h3{
font-size:1.5vw;
}

/* Anonymous 
	you can use ease-in or ease-out for smoth rotations,This is a custom rotation
  */
h1:hover{
-moz-animation:circularmotion 3s 1s forwards,
					rcircularmotion 3s 12s alternate-reverse;;
-moz-animation-timing-function:cubic-bezier{0,1.11,1,-0.05};
								
 -moz-animation-iteration-count:2; 
}

@-moz-keyframes circularmotion
{
 0%{transform:rotate(0deg);}
10%{transform:rotate(180deg);} 
20%{transform:rotate(360deg);} 
30%{transform:rotate(180deg);} 
40%{transform:rotate(360deg);}
50%{transform:rotate(180deg);}
60%{transform:rotate(360deg);} 
70%{transform:rotate(1800deg);} 
80%{transform:rotate(360deg);} 
90%{transform:rotate(150deg);}
100%{transform:rotate(350deg);}

}

@-moz-keyframes rcircularmotion
{
 0%{transform:rotate(0deg);}
10%{transform:rotate(180deg);} 
20%{transform:rotate(360deg);} 
30%{transform:rotate(180deg);} 
40%{transform:rotate(360deg);}
50%{transform:rotate(180deg);}
60%{transform:rotate(360deg);} 
70%{transform:rotate(1800deg);} 
80%{transform:rotate(360deg);} 
90%{transform:rotate(150deg);}
100%{transform:rotate(350deg);}

}
</style>
<script type="text/javascript" src="/HBIMS/mms/js/jquery-2.0.3.min.js"></script>
</head>
<body style="overflow-x: hidden;">
<html:form 	action="/transactions/IpdCNT" method="post">
<header></header>


<div class="loadingSppiner" style="position: fixed;width: 100%; height: 100%; background: rgba(0,0,0,0.9); z-index: 99999;">
	<i class="fa fa-refresh fa-spin" style="color: white; font-size: 80px; margin-top: 20%; margin-left:45%; "></i>
</div>
<div style="width: 100%; margin-left:0%;">
  <div class="container-fluid" style="background: #337ab7">
	 <h3 class="text-center" style="letter-spacing: 4px;">Bed Status Dashboard</h3> 
	  </div>
  
  
  <div name='totalBedDiv' style="position: sticky;top: 0;z-index: 999999; background:linear-gradient(to bottom, #337ab7, white);" class="container-fluid">${ipdBean.totalBedDiv}</div>
  
  
  <div class="container-fluid">
  	<div style="float: right; display:inline;">
	<a class="btn btn-info" onclick="$('#wardStatSection').fadeIn();$('#wardSection .panel').fadeOut();">Ward View</a>
	<a class="btn btn-info" onclick="$('#wardSection .panel').fadeIn();$('#wardStatSection').fadeOut();">Bed View</a>
	</div>
  </div>
  
    <bean:write name="ipdBean" property="bedStatusDash" filter="false" />
    
<hr>


	
	
 </div>
 
<!--<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm" style="top:20%; ">
      <div class="modal-content text-center" style="border-radius: 5px;">
        <div class="modal-header" style="background: linear-gradient(to top, rgba(0,200,0,0.6),white)">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="letter-spacing: 1px;">Bed Details <b></b></h4>
        </div>
         <div class="modal-body">
          <br>
          <table class="table table-striped text-left">
          	<tbody>
          		<tr>
          			<td>Availability</td><td><i class="fa fa-circle" style="color: green"></i></td>
          		</tr>
          	</tbody>
          </table>
          <!-- <button class="btn btn-info">BOOK</button> 
          <br>
        </div>
      </div>
    </div>
  </div>-->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog modal-sm modal-lg" style="top:20%; ">
      <div class="modal-content text-center" style="border-radius: 5px;">
        <div class="modal-header" style="background: linear-gradient(to top, rgba(200,0,0,0.6),white)">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h6 class="modal-title" style="letter-spacing: 1px;">Bed Details <b></b></h6>
        </div>
        <div class="modal-body">
          <br>
          <table class="table table-striped text-left">
          	<tbody>
          		<tr>
          			<td>Availability</td><td><i class="fa fa-circle" style="color: red"></i></td>
          		</tr>
          		<tr>
          			<td>Department</td><td id="patDeptName"></td>
          		</tr>
          		<tr>
          			<td>Patient Name</td><td id="patName"></td>
          		</tr>
          		<tr>
          			<td>C.R. Number</td><td id="crNo"></td>
          		</tr>
          		<tr>
          			<td>Allocation Date</td><td id="addDate"></td>
          		</tr>
          	</tbody>
          </table>
          <!-- <button class="btn btn-info disabled">BOOK</button> -->
          <br>
        </div> 
      </div>
    </div>
  </div>
 <input type='hidden' name="hmode">
 

<script type="text/javascript" src="../../hisglobal/js/jquery.min.js"></script>
<script type="text/javascript" src="/HBIMS/ipd/bedstatusdash/js/index.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>	
<script type="text/javascript" src="/HBIMS/ipd/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('canvas').each(function(){
		var canvas;
		var context;
		var al;
		var start;
		var cw;
		var ch;
		var diff;
		var bar;
        let temp = $(this).attr('id');
        canvas = document.getElementById(temp);
		context = canvas.getContext('2d');
		al=0;
		start=4.72;
		cw=context.canvas.width/2;
		ch=context.canvas.height/2;
		diff;
		bar=setInterval(progressBar,50); 


		function progressBar()
		{   var check = parseInt(canvas.getAttribute("limit")); 
			if(check>0)
			{
				diff=(al/100)*Math.PI*2;
				context.clearRect(0,0,150,150);
				context.beginPath();
				context.arc(cw,ch,50,0,2*Math.PI,false);
				context.fillStyle='#FFF';
				context.fill();
				context.strokeStyle='#dbecf9'; 
				context.stroke();
				context.fillStyle='#000';
				context.strokeStyle='#337ab7';
				context.textAlign='center';
				context.lineWidth=15;
				context.font = '10pt Verdana';
				context.beginPath();
				context.arc(cw,ch,50,start,diff+start,false);
				context.stroke();
				context.fillText(al+' %',cw+2,ch+6);
				context.fillText('Occupied',cw+2,ch+18);	
				if(al>=$(canvas).attr('limit')){
				clearTimeout(bar);
				}
				al++;
			}
			else{
				diff=(al/100)*Math.PI*2;
				context.clearRect(0,0,150,150);
				context.beginPath();
				context.arc(cw,ch,50,0,2*Math.PI,false);
				context.fillStyle='#FFF';
				context.fill();
				context.strokeStyle='#dbecf9'; 
				context.stroke();
				context.fillStyle='#000';
				context.strokeStyle='#337ab7';
				context.textAlign='center';
				context.lineWidth=15;
				context.font = '10pt Verdana'; 
				context.fillText('0%',cw+2,ch+6);
				context.fillText('Occupied',cw+2,ch+18);
				if((al-1)>=$(canvas).attr('limit')){
				clearTimeout(bar);
				}
				al++;
			}
		}
});
});
</script>


</html:form>
</body>
</html>