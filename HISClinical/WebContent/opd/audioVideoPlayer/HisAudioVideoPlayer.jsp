<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>

<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function popup(obj,eventObj)
{
	var url=obj.value;
	alert(url)
	openDependentPopup(createFHashAjaxQuery(url),eventObj,400,600,true);
	// window.open('ftp://administrator:hisregistration@10.103.0.20/dir/MOV00254.MPG','popupWindow','status=yes,scrollbars=yes,height=200,width=350,left=10,top=10');
}

</script>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,opd.*,opd.transaction.controller.fb.OpdAudioVideoUploadFB,hisglobal.utility.Entry" %>

<html:form action="/hisAudioVideoPlayer">

<his:TitleTag name="Audio Video Player">

</his:TitleTag>


	<his:ContentTag>
		<table>
			<logic:iterate id="filePaths" indexId="idx" name="<%=OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST %>"  type="hisglobal.vo.AudioVideoMasterVO">
			
				<tr>
				<td>
					 
					<%String fileName=(String)filePaths.getFileName(); 
					String filePath=Config.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+fileName;
					
					System.out.println("path of file on server"+filePath);%>
					<!--<html:radio name="AudioVideoPlayerFB" property="selectedFile" value="<%=filePath%>" onclick="popup(this,event);"></html:radio>
					-->
					<html:radio name="AudioVideoPlayerFB" property="selectedFile" value="<%=filePath%>" onclick="submitForm('PLAY');"></html:radio>
				
				<!--<a href="ftp://administrator:hisregistration@10.103.0.20/dir/Croc.wmv">gghghghghg</a>
				
				--></td>
				<td>
					<%=fileName %>
				</td>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
<his:statusInProcessWithJsp>
<%--
<OBJECT 
     classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
     codebase = "/HISClinical/opd/audioVideoPlayer"
     WIDTH = 320 HEIGHT = 300 >
     <PARAM NAME = CODE VALUE = "SimplePlayerApplet.class" >
     <PARAM NAME = ARCHIVE VALUE = "SimplePlayerApplet.jar" >
     <PARAM NAME = "type" VALUE = "application/x-java-applet;version=1.4">
     <PARAM NAME = "scriptable" VALUE = "false">
     <PARAM NAME = "file" VALUE="<%=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>">

<COMMENT>
<EMBED 
     type = "application/x-java-applet;version=1.4" 
     CODE = "SimplePlayerApplet.class" 
     ARCHIVE = "SimplePlayerApplet.jar" 
     WIDTH = 320 
     HEIGHT = 300 
     file ="<%=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>" 
     scriptable = false 
     pluginspage = "http://java.sun.com/products/plugin/index.html#download">
<NOEMBED>

</NOEMBED>
</EMBED>
</COMMENT>
</OBJECT>

--%>
<center>
<applet code="opd.audioVideoPlayer.AudioVideoPlayerApplet" 
		codebase="/HISClinical/opd/audioVideoPlayer" 
		archive="AudioVideoPlayer.jar,jmf.jar,customizer.jar,mediaplayer.jar,multiplayer.jar,sound.jar" 
		
	 	height="400" width="700">
		<param name="file" value="<%=session.getAttribute(OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER) %>">
</applet>
</center>
 </his:statusInProcessWithJsp>
 <his:ButtonToolBarTag>
		<input type="button" name="cancel" value="Cancel" onclick="submitForm('CANCEL')">
</his:ButtonToolBarTag>
 
 <center><b><his:status/></b></center>
		  			

<html:hidden name="AudioVideoPlayerFB" property="hmode"/>
</html:form>
