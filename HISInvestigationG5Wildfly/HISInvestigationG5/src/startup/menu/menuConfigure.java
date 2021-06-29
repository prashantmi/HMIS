package startup.menu;

public class menuConfigure 
{
	
	private String plusImage			= "../hisglobal/images/pl_small.gif"; ////For Plus Image in Menu Frame
	private String minusImage   		= "../hisglobal/images/mi_small.gif"; // For Minus Image in Menu Frame
	private String topMenuColor 		= "#004080"; // Font color of Top Level Menu	
	private String topMenuFontSize  	= "1"; // Font Size of Top Level Menu
	private String topMenuFont		  	= "Verdana";// Font face of Leaf Menu	
	private String leafMenuColor		= "#004080";// Font color of Leaf Menu
	private String leafMenuFontSize  	= "1";// Font Size of Leaf Menu
	private String leafMenuFont			= "Verdana"; // Font face of Leaf Menu
	private String menuPageBackColor	= "#EDE6D9"; // Menu Page Back Color   EBEBEB
	private String highLightColor		= "#800080"; //009B00"; // Color which is to be highlighted 
	//private String headerLeftImage		= "../hisglobal/images/Left50.png"; // Header Image
	private String headerLeftImage		= "../hisglobal/images/headerImage.png"; // Header Image
	private String headerMiddleImage	= "../hisglobal/images/Middle50.png"; // Header Image
	private String headerRightImage		= "../hisglobal/images/HeaderRight1.png"; // Header Image
	private String headerNewImage		= "../hisglobal/images/GeneralHeader.png"; // Header Image
	//private String initPageImage 		= "../hisglobal/images/mainPage.png"; // InitPage back ground Image
	private String initPageImage 		= "../hisglobal/images/hiswatermark.png"; // InitPage back ground Image
	private String rightFrameBackImage  = "../hisglobal/images/cdac1.psd.gif"; // back ground image to be used in transaction in right frame
	private String sessionExpired		= "10"; // time in seconds after which session will bw expired
	private String refreshTime			= "200"; //time in seconds after which header page will be refereshed
//	private String logoutImage			= "../hisglobal/images/Logout_withICO.png"; 
	private String logoutImage			= "../hisglobal/images/Logout.png";
	
	private String headerRightImagePGI	= "../hisglobal/images/PGI_HEADER_Right50.png"; // Right Header Image For PGI
	
	
	
	///////Getter
	//public String getHeaderImage( ){return this.headerImage;}
	public String getLogoutImage( ){return this.logoutImage;}
	public String getHeaderNewImage( ){return this.headerNewImage;}
	public String getLeafMenuColor(){return this.leafMenuColor;}
	public String getLeafMenuFontSize(){return this.leafMenuFontSize;}
	public String getMenuPageBackColor(){return this.menuPageBackColor;}
	public String getHighLightColor(){return this.highLightColor;}
	public String getMinusImage(){return this.minusImage;}
	public String getPlusImage(){return this.plusImage;}	
	public String getTopMenuColor(){return this.topMenuColor;}
	public String getTopMenuFont(){return this.topMenuFont;}
	public String getLeafMenuFont(){return this.leafMenuFont;}
	public String getInitPageImage(){return this.initPageImage;}
	public String getRightFrameBackImage(){return this.rightFrameBackImage;}	
	public String getTopMenuFontSize(){return this.topMenuFontSize;}
	
	public String getSessionExpired(){return this.sessionExpired;}
	public String getRefreshTime(){return this.refreshTime;}
	
	public String getHeaderLeftImage() {
		return headerLeftImage;
	}
	
	public String getHeaderMiddleImage() {
		return headerMiddleImage;
	}
	
	public String getHeaderRightImage() {
		return headerRightImage;
	}
	
	
	public String getHeaderRightImagePGI() {
		return headerRightImagePGI;
	}
	
	
	
	
}