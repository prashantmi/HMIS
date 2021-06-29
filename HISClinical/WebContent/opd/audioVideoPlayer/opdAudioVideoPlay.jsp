<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="opd.*" %>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title >HIS Audio Video Player</title>
</head>
<body>
<!--hspace="250" vspace="100"-->
	
	<applet code="registration.TestApplet" 
		codebase="/HISClinical/opd/audioVideoPlayer" 
		archive="player-applet.jar,jmf.jar,customizer.jar,mediaplayer.jar,multiplayer.jar,sound.jar" 
		height="500" width="600">
		<param name="file" value="<%=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>">
	</applet>
</body>



	

 


