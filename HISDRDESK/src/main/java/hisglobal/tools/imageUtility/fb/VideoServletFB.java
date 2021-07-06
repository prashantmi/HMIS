package hisglobal.tools.imageUtility.fb;

import org.apache.struts.action.ActionForm;

public class VideoServletFB extends ActionForm{
	
	
	private String mode;
	//for pagination
	private String recordPerPage="9";
	private String pagesPerBlock="5";
	private String pageString;
	private String currentPageNo="1";
	private String currentblock="1";
	private String primaryKey;
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	

	public String getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(String recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public String getPagesPerBlock() {
		return pagesPerBlock;
	}

	public void setPagesPerBlock(String pagesPerBlock) {
		this.pagesPerBlock = pagesPerBlock;
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

}
