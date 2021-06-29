package hisglobal.tools.imageUtility.fb;

import org.apache.struts.action.ActionForm;

public class ImageUtilityFB extends ActionForm{
	
	private String id;
	private String imageservlet;
	private String videoservlet;
	private String playerApplet;
	private String mode;
	//for pagination
	
	private String recordPerPage="5";
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

	public String getPlayerApplet() {
		return playerApplet;
	}

	public void setPlayerApplet(String playerApplet) {
		this.playerApplet = playerApplet;
	}

	public String getVideoservlet() {
		return videoservlet;
	}

	public void setVideoservlet(String videoservlet) {
		this.videoservlet = videoservlet;
	}

	public String getImageservlet() {
		return imageservlet;
	}

	public void setImageservlet(String imageservlet) {
		this.imageservlet = imageservlet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
