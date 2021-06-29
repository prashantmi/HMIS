package mrd.transaction.controller.utl;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.TabGroup;
import hisglobal.presentation.TabGroupSupport;
import mrd.MrdConfig;

public class ReceiveCertificateInMrdDeskUTL extends ControllerUTIL
{
	static TabGroup objTabGroup=new TabGroup();
	 static	 
	 {
		 TabGroupSupport t1=new TabGroupSupport(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_ACCEPT,0,TabGroupSupport.TAB_UNSELECTED,"Accept");
		 TabGroupSupport t2=new TabGroupSupport(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_ARCHIVAL,1,TabGroupSupport.TAB_UNSELECTED,"Archival");
		 TabGroupSupport t3=new TabGroupSupport(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_LOSTFOUND,2,TabGroupSupport.TAB_UNSELECTED,"Found");
		 
		// Tab t4=new Tab(AlertConfig.ALERT_DESK_MODE_TRACKING,3,Tab.TAB_UNSELECTED,"Tracking");
		 objTabGroup.addTab(t1);
		 objTabGroup.addTab(t2);
		 objTabGroup.addTab(t3);
		 objTabGroup.setHtmlModeFieldName(MrdConfig.TAB_DSK_HTML_MODE_FIELD_NAME);
		 objTabGroup.setJsOnClickFuncName(MrdConfig.TAB_DSK_JS_FUNC_ON_CLICK);
	 }
	 
	 public static void setTabSequence(String _selectedTab ,HttpServletRequest request)	 
	 {
		 
		 Iterator it =objTabGroup.getTabsCollection().iterator();
		 while(it.hasNext())
		  { 
			 TabGroupSupport tb=(TabGroupSupport)it.next();
			tb.setStatus(TabGroupSupport.TAB_UNSELECTED);
		 }	 
		 
		 objTabGroup.setSelectedTab(_selectedTab);		 
		 
		 request.setAttribute(MrdConfig.TAB_GROUP,objTabGroup);		 
	 }
}
