package opd.master.controller.util;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import registration.RegistrationConfig;
import registration.controller.action.Tab;
import registration.controller.action.TabGroup;
import hisglobal.presentation.ControllerUTIL;

public class IcdMasterUTIL extends ControllerUTIL {

	
	static TabGroup objTabGroup=new TabGroup();
	 static	 {
		 		 
		 Tab t1=new Tab(OpdConfig.OPD_DESKMODE_ICDMASTER_GROUP,0,Tab.TAB_UNSELECTED,"ICD-10 Group");
		 Tab t2=new Tab(OpdConfig.OPD_DESKMODE_ICDMASTER_SUBGROUP,1,Tab.TAB_UNSELECTED,"ICD-10 Sub Group");
		 Tab t3=new Tab(OpdConfig.OPD_DESKMODE_ICDMASTER_DISEASE,2,Tab.TAB_UNSELECTED,"ICD-10 Disease");	
		 Tab t4=new Tab(OpdConfig.OPD_DESKMODE_ICDMASTER_SUBDISEASE,3,Tab.TAB_UNSELECTED,"ICD-10 Sub Disease");
		 objTabGroup.addTab(t1);
		 objTabGroup.addTab(t2);
		 objTabGroup.addTab(t3);
		 objTabGroup.addTab(t4);
		 objTabGroup.setHtmlModeFieldName(RegistrationConfig.REG_DSK_HTML_MODE_FIELD_NAME);
		 objTabGroup.setJsOnClickFuncName(RegistrationConfig.REG_DSK_JS_FUNC_ON_CLICK); 
		 
	    } 
	 
	 public static void setTabSequence(String _selectedTab ,HttpServletRequest request)	 
	 {
		 
		 Iterator it =objTabGroup.getTabsCollection().iterator();
		 while(it.hasNext())
		  { 
			Tab tb=(Tab)it.next();
			tb.setStatus(Tab.TAB_UNSELECTED);
		 }	 
		 
		 objTabGroup.setSelectedTab(_selectedTab);		 
		 
		 request.setAttribute(RegistrationConfig.TAB_GROUP,objTabGroup);		 
	 }
	 
	
}
