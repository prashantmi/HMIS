package hisglobal.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import hisglobal.config.HISConfig;
import hisglobal.vo.ValueObject;


/***************************Start of program*****************************\
## Copyright Information				: C-DAC, Noida  
## Project Name							: G5 HIMS
## Name of Developer		 			: Singaravelan & Garima
## Module Name							: Alert Management
## Process/Database Object Name			: GBLT_ALERT_MST
## Purpose								: Alert Management Integration in G5 LOgin Desk
## Date of Creation						: 21-Nov-2014
## Modification Log						:				
##		Modify Date						: 
##		Reason	(CR/PRS)				: 
##		Modify By						: 
*/

public class CommonAlertVO extends ValueObject implements Comparable
{
	private String alertCode;
	private String alertName;
	private String moduleName;
	private String alertPriority;
	private String userBased;
	private String alertQuery;
	private String title;
	private String priority;
	private String alertMsg;
	private String cat;
	private String alertTime;
	private String color;
	private String alertID;
	private String serialNo;
	private String sortType;
	private String orderBy;
	private String notifiableFlag;
	private String assignToSeatID;
	private String uniqueKey;
	private String alertMode;
	private String parentAlertID;
	private String remarks;
	private String alertTitle;
	private String alertShortMsg;
	private String alertLongMsg;
	private String global;
	private String alertCatName;
	private String notification;
	private String alertExpiry;
	private String assignRemarks;
	private String alertAction;
	private String assignUser;
	private String popupFlag;
	private String popupQuery;
	private String popupHeader;
	private String popupRoleQuery;
	private String popupRoleBased;
	private String isMarquee;


	public String getAssignUser()
	{
		return assignUser;
	}

	public void setAssignUser(String assignUser)
	{
		this.assignUser = assignUser;
	}

	public String getAssignRemarks() {
		return assignRemarks;
	}

	public void setAssignRemarks(String assignRemarks) {
		this.assignRemarks = assignRemarks;
	}

	public String getAlertExpiry() {
		return alertExpiry;
	}

	public void setAlertExpiry(String alertExpiry) {
		this.alertExpiry = alertExpiry;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAssignToSeatID() {
		return assignToSeatID;
	}

	public void setAssignToSeatID(String assignToSeatID) {
		this.assignToSeatID = assignToSeatID;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getAlertMode() {
		return alertMode;
	}

	public void setAlertMode(String alertMode) {
		this.alertMode = alertMode;
	}

	public String getParentAlertID() {
		return parentAlertID;
	}

	public void setParentAlertID(String parentAlertID) {
		this.parentAlertID = parentAlertID;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}
	

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public String getAlertCode()
	{
		return alertCode;
	}

	public void setAlertCode(String alertCode)
	{
		this.alertCode = alertCode;
	}

	public String getAlertName()
	{
		return alertName;
	}

	public void setAlertName(String alertName)
	{
		this.alertName = alertName;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getAlertPriority()
	{
		return alertPriority;
	}

	public void setAlertPriority(String alertPriority)
	{
		this.alertPriority = alertPriority;
	}

	public String getUserBased()
	{
		return userBased;
	}

	public void setUserBased(String userBased)
	{
		this.userBased = userBased;
	}

	public String getAlertQuery()
	{
		return alertQuery;
	}

	public void setAlertQuery(String alertQuery)
	{
		this.alertQuery = alertQuery;
	}

	public String getAlertID() {
		return alertID;
	}

	public void setAlertID(String alertID) {
		this.alertID = alertID;
	}
	
	public String getNotifiableFlag() {
		return notifiableFlag;
	}

	public void setNotifiableFlag(String notifiableFlag) {
		this.notifiableFlag = notifiableFlag;
	}

	public String getAlertTitle() {
		return alertTitle;
	}

	public void setAlertTitle(String alertTitle) {
		this.alertTitle = alertTitle;
	}

	public String getAlertShortMsg() {
		return alertShortMsg;
	}

	public void setAlertShortMsg(String alertShortMsg) {
		this.alertShortMsg = alertShortMsg;
	}

	public String getAlertLongMsg() {
		return alertLongMsg;
	}

	public void setAlertLongMsg(String alertLongMsg) {
		this.alertLongMsg = alertLongMsg;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getAlertCatName() {
		return alertCatName;
	}

	public void setAlertCatName(String alertCatName) {
		this.alertCatName = alertCatName;
	}
	
	
	
	
	public int compareTo(Object o)
	{
		int flag = 0;
		CommonAlertVO comAlertVO=(CommonAlertVO)o;
		if(comAlertVO.getOrderBy().equals(HISConfig.ORDER_BY_ALERT_NAME)) flag=compareByAlertName(comAlertVO);
		if(comAlertVO.getOrderBy().equals(HISConfig.ORDER_BY_ALERT_CATEGORY)) flag=compareByAlertCategory(comAlertVO);
		if(comAlertVO.getOrderBy().equals(HISConfig.ORDER_BY_ALERT_PRIORITY)) flag=compareByAlertPriority(comAlertVO);
		if(comAlertVO.getOrderBy().equals(HISConfig.ORDER_BY_ALERT_DATE)) flag=compareByAlertDate(comAlertVO);
		return flag;
	}	
	
	public int compareByAlertName(CommonAlertVO comAlertVO)
	{
		int flag = 0;
		if(comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_ASC))
			flag = alertName.compareToIgnoreCase(comAlertVO.getAlertName());
			
		else if (comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_DSC))
		{
			flag =comAlertVO.getAlertName().compareToIgnoreCase(alertName);
		}
		return flag;
	}
	
	public int compareByAlertCategory(CommonAlertVO comAlertVO)
	{
		int flag=0;
		
		if(comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_ASC))
			flag = cat.compareToIgnoreCase(comAlertVO.getCat());
	
		else if (comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_DSC)) 
		{
			flag = comAlertVO.getCat().compareToIgnoreCase(cat);
		}
		return flag;
	}
	
	public int compareByAlertPriority(CommonAlertVO comAlertVO)
	{
		int flag=0;
		
		if(comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_ASC))
			flag = priority.compareTo(comAlertVO.getPriority());
			
		else if (comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_DSC))
		{
			flag = comAlertVO.getPriority().compareTo(priority);
		}
		return flag;
	}
	
	public int compareByAlertDate(CommonAlertVO comAlertVO)
	{
		int flag=0;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
			Date convertedDate = dateFormat.parse(alertTime);
			Date convertedDateVO = dateFormat.parse(comAlertVO.getAlertTime()); 
		 
			if(comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_ASC))
				flag = convertedDate.compareTo(convertedDateVO);
			
			else if (comAlertVO.getSortType().equals(HISConfig.SORT_TYPE_DSC)) 
			{
				flag = convertedDateVO.compareTo(convertedDate);
			} 
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		 
		/*if(comAlertVO.getSortType().equals(Config.SORT_TYPE_ASC))
			flag = alertTime.compareTo(comAlertVO.getAlertTime());
		
		else if (comAlertVO.getSortType().equals(Config.SORT_TYPE_DSC)) 
		{
			flag = comAlertVO.getAlertTime().compareTo(alertTime);
		} */
		return flag;
	}

	public String getAlertAction()
	{
		return alertAction;
	}

	public void setAlertAction(String alertAction)
	{
		this.alertAction = alertAction;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPopupFlag() {
		return popupFlag;
	}

	public void setPopupFlag(String popupFlag) {
		this.popupFlag = popupFlag;
	}

	public String getPopupQuery() {
		return popupQuery;
	}

	public void setPopupQuery(String popupQuery) {
		this.popupQuery = popupQuery;
	}

	public String getPopupHeader() {
		return popupHeader;
	}

	public void setPopupHeader(String popupHeader) {
		this.popupHeader = popupHeader;
	}

	public String getPopupRoleQuery() {
		return popupRoleQuery;
	}

	public void setPopupRoleQuery(String popupRoleQuery) {
		this.popupRoleQuery = popupRoleQuery;
	}

	public String getPopupRoleBased() {
		return popupRoleBased;
	}

	public void setPopupRoleBased(String popupRoleBased) {
		this.popupRoleBased = popupRoleBased;
	}

	public String getIsMarquee() {
		return isMarquee;
	}

	public void setIsMarquee(String isMarquee) {
		this.isMarquee = isMarquee;
	}

	

	
	
}
