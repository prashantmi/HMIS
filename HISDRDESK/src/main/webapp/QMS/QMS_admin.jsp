<!DOCTYPE html>
<html>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.collections.MultiMap"%>
<%@page import="org.apache.commons.collections.map.MultiValueMap"%>
<%@page import="org.json.*"%>
<head>
<title>Testing websockets</title>
<meta charset="utf-8" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>  
	
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>


<script type="text/javascript">
		var webSocket = 
			new WebSocket('ws://10.226.17.20:8082/HISDRDESK/QMSTest');

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			/* document.getElementById('messages').innerHTML 
				+= '<br />Received message: ' + event.data; */
		}

		function onOpen(event) {
			/* document.getElementById('messages').innerHTML 
				= 'Connection established'; */
		}

		function onError(event) {
			alert(event.data);
		}

		function send() {
			//var txt = document.getElementById('inputmessage').value;
			webSocket.send();
			return false;
		}
	</script>
	
	
	
<script type="text/javascript">
function getDeltList(e)
 {
	//alert('1');
	//alert(e.value); 
	document.forms[0].hmode.value="NEW";
	document.forms[0].submit();
 }
//var index=0;
function generateQNo(e , roomId){

	//alert( document.getElementById('roomwisePatCountId'+roomId).value);
	
	var patdtls= document.getElementById(roomId).innerHTML ;
	//alert(patdtls);
	var jsonArrayobj=  JSON.parse(patdtls); //JSON.stringify(patdtls);
	//alert(jsonArrayobj);
	//alert(jsonArrayobj[index].PATCRNO);
	//alert(parseInt(document.getElementById('roomwisePatCountId'+roomId).value));
	if( (parseInt(jsonArrayobj.length)-1) < parseInt(document.getElementById('roomwisePatCountId'+roomId).value)){
				alert('No More Patient Found !!!!!');
		}else{
			var index=document.getElementById('roomwisePatCountId'+roomId).value;
			webSocket.send(JSON.stringify(jsonArrayobj[index]).toString());
			document.getElementById('roomwisePatCountId'+roomId).value=(parseInt(document.getElementById('roomwisePatCountId'+roomId).value)+1);
			}
	
	//alert('');
	
}

</script>

<body>
<html:form action="/transaction/QMSAction.cnt"  name="QmsFB" type="QMS.transaction.controller.fb.QmsFB" method="post"  >
	 <div id="messages"></div> 
	<div align="center">
	<div class="col-xs-12 col-sm-5 form-horizontal">
          		<div class="form-group" style="margin-bottom:0px;">
          		<p style="color: #0b3da1; font-weight: bold;padding-top:0" class="control-label col-xs-4 col-sm-3" for="deptUnitName">DEPT/UNIT </p>
                <div class="col-xs-16 col-sm-16" style="">
                  <select name="deptUnitName" id="deptUnitName" class="form-control" style="color: #082b71;border-radius: 0px;" onchange="getDeltList(this);">
            		 <option value="0#0#0">Select Value</option> 
                    <%
                    HashMap<String ,String> DeptCMB= (HashMap) request.getSession().getAttribute("QMSDEPTDTL");
                   	//System.out.println("DeptCMB"+DeptCMB);
                   	if(DeptCMB!=null)
                   	{
                   	for(Map.Entry m:DeptCMB.entrySet()){ 
                   	 String DeptCode=(String)m.getKey();
            		 String DeptName=(String)m.getValue();
            		 String SelectedDeptId=(String)request.getSession().getAttribute("SelectedDeptId");
            		 if(SelectedDeptId.equalsIgnoreCase(DeptCode))
            		 {
            			 %>
                      	<option value="<%=DeptCode%>" selected><%=DeptName %></option>
                     
                      <% 
            		 }else
            		 {
                   	 %>
                     	<option value="<%=DeptCode%>"><%=DeptName %></option>
                    
                     <%
            		 }
                   		}	
                     }
                     %>
                   
            	  </select>
                </div>
          		</div>
          	</div>
          	
          	
          	
          	<%
          			MultiValueMap RoomWise= (MultiValueMap) request.getSession().getAttribute("ROOMWISEDTL");
                   	//System.out.println("DeptCMB"+RoomWise);
                   	if(RoomWise!=null)
                   	{
                   		Set<String> keys = RoomWise.keySet();
                   	for(String key : keys){ 
                   		System.out.println("RoomNo"+key);
                   		System.out.println("Patient Dtl"+RoomWise.get(key));
                   		System.out.println("Patient Dtl"+((List) RoomWise.getCollection(key)).size());
                   		
                   		%>
                   	<br>	
					<div class="col-sm-5" style="">
					   <div class="card" style="background-color: #070606;color: #fbf7f7;">
					    <div class="card-body">
					      <div class="row">
					        <div class="col-sm-2" align="">
					          <b style="font-size: 4rem;color: #f5d307"></b>
					        </div>
					        
					        
					        <div class="col-sm-8">
					           <b style="font-size: 1.5rem;color: #077df5" ><label><%=key.split("#")[1]%></label ></b>
					        </div>
					      </div>
					    </div>
					      <input type="button" name="Next" value="Next" onclick="generateQNo(this ,<%=key.split("#")[0]%> )">
					     <div style="display: none;" id=<%=key.split("#")[0]%> ><%=RoomWise.get(key)%> </div> 
					      <input type="hidden" id="roomwisePatCountId<%=key.split("#")[0]%>" name="roomwisePatCount<%=key.split("#")[0]%>" value="0"   />
					      <input type="hidden" id="roomwiseMaxPatCountId<%=key.split("#")[0]%>" name="roomwiseMaxPatCount<%=key.split("#")[0]%>" value=<%=((List) RoomWise.getCollection(key)).size() %>   /> 
					      </div>
					  </div>
					
					  <br>
                   		
                   		<%
                   		}	
                     }
                     %>
	
	<!-- <div>
		<input id="inputmessage" type="text" />
	</div> -->
	<div>
		<!-- <input type="submit" value="Broadcast message" onclick="send()" /> -->
	</div>
</div>
	
	
</body>
  <input type="hidden" name="hmode"/> 
</html:form >
</html>