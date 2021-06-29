package opd.transaction.controller.util;

/*
 * @ author Pragya Sharma
 * Creation Date : 23-Sep-2011
 */

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.TabGroup;
import hisglobal.presentation.TabGroupSupport;

public class ICDEntryFormDeskUTL extends ControllerUTIL
{
	static TabGroup objTabGroup = new TabGroup();
	static
	{
		TabGroupSupport t1 = new TabGroupSupport(OpdConfig.ICD_ENTRY_FORM_NEW_ENTRY, 0, TabGroupSupport.TAB_UNSELECTED, "New Entry");
		TabGroupSupport t2 = new TabGroupSupport(OpdConfig.ICD_ENTRY_FORM_MODIFICATION, 1, TabGroupSupport.TAB_UNSELECTED, "Modification");

		objTabGroup.addTab(t1);
		objTabGroup.addTab(t2);

		objTabGroup.setHtmlModeFieldName(OpdConfig.TAB_DSK_HTML_MODE_FIELD_NAME);
		objTabGroup.setJsOnClickFuncName(OpdConfig.TAB_DSK_JS_FUNC_ON_CLICK);
	}

	public static void setTabSequence(String _selectedTab, HttpServletRequest request)
	{

		Iterator it = objTabGroup.getTabsCollection().iterator();
		while (it.hasNext())
		{
			TabGroupSupport tb = (TabGroupSupport) it.next();
			tb.setStatus(TabGroupSupport.TAB_UNSELECTED);
		}

		objTabGroup.setSelectedTab(_selectedTab);

		request.setAttribute(OpdConfig.TAB_GROUP, objTabGroup);
	}
}
