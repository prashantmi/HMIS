package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import hisglobal.hisconfig.Config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeskMasterFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String controls[];
	private String isActive;

	private String deskId;

	private String deskName;
	private String deskType;
	private String deskTypeDesc;
	
	private String deskMenuId;
	private String location;
	private String displayOrder;
	private String userDeskMenuName;
	private String color;
	
	private ArrayList menusList;
	private ArrayList modLeftMenuList; 
	private ArrayList modRightMenuList; 
	private ArrayList modTopMenuList; 
	private ArrayList modBottomMenuList;
	
	private String[] leftMenuList; 
	private String[] rightMenuList; 
	private String[] topMenuList; 
	private String[] bottomMenuList;
	private String isDefault;
	private String checkForDefault;
	private String currentHmode;
	
	//added for Template Mapping in Desk
	private Boolean isTemp = false;
	private String TempId ;
	
	public String getCheckForDefault() {
		return checkForDefault;
	}

	public void setCheckForDefault(String checkForDefault) {
		this.checkForDefault = checkForDefault;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public DeskMasterFB() 
	{
		this.hmode="";
		this.controls=new String[1];
		
		this.deskId="";
		
		this.deskName="";
		this.deskType=OpdConfig.DESK_TYPE_OPD;
		
		this.deskMenuId="";
		this.location="";
		this.displayOrder="";
		this.userDeskMenuName="";
		this.color="";
		this.isDefault = "0";
		this.isActive=Config.IS_VALID_ACTIVE;
		
		this.menusList=null;;
		
		this.modLeftMenuList=new ArrayList(); 
		this.modRightMenuList=new ArrayList(); 
		this.modTopMenuList=new ArrayList(); 
		this.modBottomMenuList=new ArrayList();

		this.leftMenuList=new String[0]; 
		this.rightMenuList=new String[0]; 
		this.topMenuList=new String[0]; 
		this.bottomMenuList=new String[0];
		
		//this.isDefault ="off";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		super.reset(mapping,request);

		this.deskId="";
		
		this.deskName="";
		this.deskTypeDesc="";
		
		this.deskMenuId="";
		this.location="";
		this.displayOrder="";
		this.userDeskMenuName="";
		this.color="";
		this.isDefault = "0";
		this.isActive=Config.IS_VALID_ACTIVE;
		
		this.menusList=null;;
		
		this.modLeftMenuList=new ArrayList(); 
		this.modRightMenuList=new ArrayList(); 
		this.modTopMenuList=new ArrayList(); 
		this.modBottomMenuList=new ArrayList();

		this.leftMenuList=new String[0]; 
		this.rightMenuList=new String[0]; 
		this.topMenuList=new String[0]; 
		this.bottomMenuList=new String[0];	

		//this.isDefault ="off";
	}
	
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserDeskMenuName() {
		return userDeskMenuName;
	}
	public void setUserDeskMenuName(String userDeskMenuName) {
		this.userDeskMenuName = userDeskMenuName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getBottomMenuList() {
		return bottomMenuList;
	}
	public void setBottomMenuList(String[] bottomMenuList) {
		this.bottomMenuList = bottomMenuList;
	}
	public String[] getLeftMenuList() {
		return leftMenuList;
	}
	public void setLeftMenuList(String[] leftMenuList) {
		this.leftMenuList = leftMenuList;
	}
	public String[] getRightMenuList() {
		return rightMenuList;
	}
	public void setRightMenuList(String[] rightMenuList) {
		this.rightMenuList = rightMenuList;
	}
	public String[] getTopMenuList() {
		return topMenuList;
	}
	public void setTopMenuList(String[] topMenuList) {
		this.topMenuList = topMenuList;
	}
	public ArrayList getMenusList() {
		return menusList;
	}
	public void setMenusList(ArrayList menusList) {
		this.menusList = menusList;
	}
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getDeskName() {
		return deskName;
	}
	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public ArrayList getModBottomMenuList() {
		return modBottomMenuList;
	}

	public void setModBottomMenuList(ArrayList modBottomMenuList) {
		this.modBottomMenuList = modBottomMenuList;
	}

	public ArrayList getModLeftMenuList() {
		return modLeftMenuList;
	}

	public void setModLeftMenuList(ArrayList modLeftMenuList) {
		this.modLeftMenuList = modLeftMenuList;
	}

	public ArrayList getModRightMenuList() {
		return modRightMenuList;
	}

	public void setModRightMenuList(ArrayList modRightMenuList) {
		this.modRightMenuList = modRightMenuList;
	}

	public ArrayList getModTopMenuList() {
		return modTopMenuList;
	}

	public void setModTopMenuList(ArrayList modTopMenuList) {
		this.modTopMenuList = modTopMenuList;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDeskTypeDesc() {
		return deskTypeDesc;
	}

	public void setDeskTypeDesc(String deskTypeDesc) {
		this.deskTypeDesc = deskTypeDesc;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getCurrentHmode() {
		return currentHmode;
	}

	public void setCurrentHmode(String currentHmode) {
		this.currentHmode = currentHmode;
	}

	public Boolean getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(Boolean isTemp) {
		this.isTemp = isTemp;
	}

	public String getTempId() {
		return TempId;
	}

	public void setTempId(String tempId) {
		TempId = tempId;
	}
}
