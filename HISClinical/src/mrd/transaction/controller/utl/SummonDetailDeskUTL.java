package mrd.transaction.controller.utl;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.TabGroup;
import hisglobal.presentation.TabGroupSupport;

public class SummonDetailDeskUTL extends ControllerUTIL
{
	static TabGroup objTabGroup = new TabGroup();
	static
	{
		TabGroupSupport t1 = new TabGroupSupport(MrdConfig.SUMMON_DETAIL_UPLOAD_MODE_COMPUTERIZED, 0, TabGroupSupport.TAB_UNSELECTED, "Computerized");
		TabGroupSupport t2 = new TabGroupSupport(MrdConfig.SUMMON_DETAIL_UPLOAD_MODE_MANUAL, 1, TabGroupSupport.TAB_UNSELECTED, "Manual");

		objTabGroup.addTab(t1);
		objTabGroup.addTab(t2);

		objTabGroup.setHtmlModeFieldName(MrdConfig.TAB_DSK_HTML_MODE_FIELD_NAME);
		objTabGroup.setJsOnClickFuncName(MrdConfig.TAB_DSK_JS_FUNC_ON_CLICK);
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

		request.setAttribute(MrdConfig.TAB_GROUP, objTabGroup);
	}
}
