package usermgmt.reports;

public class UserProfileBean
{
	private String userID;
	private String userSeatID;
	private String userName;
	private String ipAddress;
	private boolean isLoggedIn;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public UserProfileBean(boolean b)
	{
		this.isLoggedIn=b;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSeatID() {
		return userSeatID;
	}
	public void setUserSeatID(String userSeatID) {
		this.userSeatID = userSeatID;
	}
}
