package mrd.transaction.controller.utl;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import mrd.MrdConfig;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.TabGroup;
import hisglobal.presentation.TabGroupSupport;

public class BirthDeathRegistrationUploadDeskUTL extends ControllerUTIL
{
	static TabGroup objTabGroup=new TabGroup();
	 static	 
	 {
		 TabGroupSupport t1=new TabGroupSupport(MrdConfig.BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_BIRTH,0,TabGroupSupport.TAB_UNSELECTED,"Birth");
		 TabGroupSupport t2=new TabGroupSupport(MrdConfig.BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_DEATH,1,TabGroupSupport.TAB_UNSELECTED,"Death");
		 
		// Tab t4=new Tab(AlertConfig.ALERT_DESK_MODE_TRACKING,3,Tab.TAB_UNSELECTED,"Tracking");
		 objTabGroup.addTab(t1);
		 objTabGroup.addTab(t2);
		 // objTabGroup.addTab(t4);
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
