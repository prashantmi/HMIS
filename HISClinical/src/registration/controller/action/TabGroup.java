package registration.controller.action;

import java.util.*;

public class TabGroup {	


	
	private Collection tabsCollection=new TreeSet(new Tab.TabComparator());
	private  String jsOnClickFuncName;
	private String htmlModeFieldName;
	
	
	public String getHtmlModeFieldName() {
		return htmlModeFieldName;
	}
	public void setHtmlModeFieldName(String htmlModeFieldName) {
		this.htmlModeFieldName = htmlModeFieldName;
	}
	public String getJsOnClickFuncName() {
		return jsOnClickFuncName;
	}
	public void setJsOnClickFuncName(String jsOnClickFuncName) {
		this.jsOnClickFuncName = jsOnClickFuncName;
	}
	public Collection getTabsCollection() {
		return tabsCollection;
	}
	public void setTabsCollection(Collection tabsCollection) {
		this.tabsCollection = tabsCollection;
	}	
	public void  setSelectedTab(String _selectedTabName)	
	{
	  Iterator it =this.tabsCollection.iterator();
	  while (it.hasNext())
	  {
		  Tab objTab=(Tab)it.next();
		  System.out.println("TabName()"+objTab.getTabName());
		  System.out.println("_selectedTabName"+_selectedTabName);
		  if(objTab.getTabName().equals(_selectedTabName))
		  {
			 System.out.println("inside if objTab.getTabName()");			 
			 objTab.setStatus(Tab.TAB_SELECTED);
			// objTab.setStatus(1);	
			 System.out.println("objTab.getStatus()::::"+objTab.getStatus());			 
		  }		  
	  }		
	}
	
	
	
	public void addTab(Tab _tab)
	{	
		this.tabsCollection.add(_tab);		
	}	
	
	
}	