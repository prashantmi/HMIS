package hisglobal.tools.imageUtility.fb;

import org.apache.struts.action.ActionForm;

public class ImageServletFB extends ActionForm{
	
	
	private String mode;
	//for pagination
	private String currentPageNo="1";
	private String currentblock="1";
	public static final String recordPerPage="12";
	public static final String pagesPerBlock="5";
	private String lengthofImageList;
	private String mapKey;
	private String primaryKey;	
	public String getScreenType() {
		return ScreenType;
	}

	public void setScreenType(String screenType) {
		ScreenType = screenType;
	}

	private String ScreenType;
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	private String pageString;
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getRecordPerPage() {
		return recordPerPage;
	}

	

	public String getPagesPerBlock() {
		return pagesPerBlock;
	}

	

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
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

}
