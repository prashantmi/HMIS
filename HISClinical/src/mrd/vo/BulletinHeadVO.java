package mrd.vo;
import java.util.List;

import hisglobal.vo.ValueObject;

public class BulletinHeadVO extends ValueObject
{

	private String bulletinId;
	private String headingId;
	private String headingName;
	private String displayOrder;
	private String isValid;
	private String seatId;
	private String hospitalCode;
	private String displayHTML;
	private String ftl;
	private String dataquery;
	private List<BulletinHeadDataVO> headData;
	private String isPageBreakAfter;
	
	
	public String getBulletinId() {
		return bulletinId;
	}
	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}
	public String getHeadingId() {
		return headingId;
	}
	public void setHeadingId(String headingId) {
		this.headingId = headingId;
	}
	public String getHeadingName() {
		return headingName;
	}
	public void setHeadingName(String headingName) {
		this.headingName = headingName;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getFtl() {
		return ftl;
	}
	public void setFtl(String ftl) {
		this.ftl = ftl;
	}
	public String getDataquery() {
		return dataquery;
	}
	public void setDataquery(String dataquery) {
		this.dataquery = dataquery;
	}
	public List<BulletinHeadDataVO> getHeadData() {
		return headData;
	}
	public void setHeadData(List<BulletinHeadDataVO> headData) {
		this.headData = headData;
	}
	public String getDisplayHTML()
	{
		return displayHTML;
	}
	public void setDisplayHTML(String displayHTML)
	{
		this.displayHTML = displayHTML;
	}
	public String getIsPageBreakAfter()
	{
		return isPageBreakAfter;
	}
	public void setIsPageBreakAfter(String isPageBreakAfter)
	{
		this.isPageBreakAfter = isPageBreakAfter;
	}
}