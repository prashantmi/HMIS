package hisglobal.tools.imageUtility.fb;

import org.apache.struts.action.ActionForm;

public class CommonServletFB extends ActionForm{
	
	
	private String mode;
	//for pagination
	private String currentPageNo="1";
	private String currentblock="1";
	
	private String lengthofImageList;
	private String mapKey;
	private String primaryKey;
	private String pageString;
	
	

	
	public static final String recordPerPage="12";
	public static final String pagesPerBlock="5";
	private String ScreenType;
	
	
	public String getScreenType() {
		return ScreenType;
	}
	public void setScreenType(String screenType) {
		ScreenType = screenType;
	}
	public static String getRecordperpage() {
		return recordPerPage;
	}
	public static String getPagesperblock() {
		return pagesPerBlock;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public String getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(String currentblock) {
		this.currentblock = currentblock;
	}
	
	public String getLengthofImageList() {
		return lengthofImageList;
	}
	public void setLengthofImageList(String lengthofImageList) {
		this.lengthofImageList = lengthofImageList;
	}
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	
	
}
