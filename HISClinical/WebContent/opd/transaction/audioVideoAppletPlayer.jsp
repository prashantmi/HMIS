<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="opd.OpdConfig"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<html:form action="/opdDocumentArchival">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TransactionContainer>

<his:TitleTag name="Audio Video Player">
</his:TitleTag>
<%//=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>
<center>

<applet code="opd.audioVideoPlayer.AudioVideoPlayerApplet" 
		codebase="/HISClinical/opd/audioVideoPlayer" 
		archive="AudioVideoPlayer.jar,jmf.jar,customizer.jar,mediaplayer.jar,multiplayer.jar,sound.jar" 
		
	 	height="400" width="700">
		<param name="file" value="<%=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>">
		
</applet>
</center>

 <his:ButtonToolBarTag>
		<input type="button" name="cancel" value="Cancel" onclick="self.close();" onkeypress="if(event.keyCode==13) self.close();" tabindex="1">
</his:ButtonToolBarTag>
</his:TransactionContainer>
</html:form>