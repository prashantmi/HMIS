<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="div-table">
			 	<s:set name="shiftKeyMap" value="#session.multiRowMap"></s:set>
				 'length:<s:property value="%{#shiftKeyMap.size}"/>'
				 <s:iterator id="countShiftVO" value="%{#shiftKeyMap}" status="statusMap">
				 	<s:set name="shiftKey" value="key"></s:set>
				 	<s:set name="shiftValue" value="value"></s:set>
				 		<div class="div-table-row ">
						<div class="div-table-col column  width100">
							'shiftKey :<s:property value="%{#shiftKey}"/>',
							'shiftName :<s:property value="%{#shiftValue.shiftName}"/>'
						</div>
						</div>
				 </s:iterator>
			 </div>	
				
</body>
</html>