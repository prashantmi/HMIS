package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GBLT_MENU_MST database table.
 * 
 */
@Entity
@Table(name="GBLT_MENU_MST")
@NamedQuery(name="GbltMenuMst.findAll", query="SELECT g FROM GbltMenuMst g")
public class GbltMenuMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GbltMenuMstPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_ENTRY_DATE")
	private Date gdtEntryDate;

	@Column(name="GNUM_DISPLAY_ORDER")
	private BigDecimal gnumDisplayOrder;

	@Column(name="GNUM_HL7_CODE")
	private BigDecimal gnumHl7Code;

	@Column(name="GNUM_ISPORTAL")
	private BigDecimal gnumIsportal;

	@Column(name="GNUM_ISVALID")
	private BigDecimal gnumIsvalid;

	@Column(name="GNUM_MENU_LEVEL")
	private BigDecimal gnumMenuLevel;

	@Column(name="GNUM_MENU_POSITION")
	private BigDecimal gnumMenuPosition;

	@Column(name="GNUM_MODULE_ID")
	private BigDecimal gnumModuleId;

	@Column(name="GNUM_PARENT_ID")
	private BigDecimal gnumParentId;

	@Column(name="GNUM_SEAT_ID")
	private BigDecimal gnumSeatId;

	@Column(name="GSTR_JS_FUNCTION_NAME")
	private String gstrJsFunctionName;

	@Column(name="GSTR_MENU_NAME")
	private String gstrMenuName;

	@Column(name="GSTR_MENUCLASS_ID")
	private String gstrMenuclassId;

	@Column(name="GSTR_URL")
	private String gstrUrl;

	public GbltMenuMst() {
	}

	public GbltMenuMstPK getId() {
		return this.id;
	}

	public void setId(GbltMenuMstPK id) {
		this.id = id;
	}

	public Date getGdtEntryDate() {
		return this.gdtEntryDate;
	}

	public void setGdtEntryDate(Date gdtEntryDate) {
		this.gdtEntryDate = gdtEntryDate;
	}

	public BigDecimal getGnumDisplayOrder() {
		return this.gnumDisplayOrder;
	}

	public void setGnumDisplayOrder(BigDecimal gnumDisplayOrder) {
		this.gnumDisplayOrder = gnumDisplayOrder;
	}

	public BigDecimal getGnumHl7Code() {
		return this.gnumHl7Code;
	}

	public void setGnumHl7Code(BigDecimal gnumHl7Code) {
		this.gnumHl7Code = gnumHl7Code;
	}

	public BigDecimal getGnumIsportal() {
		return this.gnumIsportal;
	}

	public void setGnumIsportal(BigDecimal gnumIsportal) {
		this.gnumIsportal = gnumIsportal;
	}

	public BigDecimal getGnumIsvalid() {
		return this.gnumIsvalid;
	}

	public void setGnumIsvalid(BigDecimal gnumIsvalid) {
		this.gnumIsvalid = gnumIsvalid;
	}

	public BigDecimal getGnumMenuLevel() {
		return this.gnumMenuLevel;
	}

	public void setGnumMenuLevel(BigDecimal gnumMenuLevel) {
		this.gnumMenuLevel = gnumMenuLevel;
	}

	public BigDecimal getGnumMenuPosition() {
		return this.gnumMenuPosition;
	}

	public void setGnumMenuPosition(BigDecimal gnumMenuPosition) {
		this.gnumMenuPosition = gnumMenuPosition;
	}

	public BigDecimal getGnumModuleId() {
		return this.gnumModuleId;
	}

	public void setGnumModuleId(BigDecimal gnumModuleId) {
		this.gnumModuleId = gnumModuleId;
	}

	public BigDecimal getGnumParentId() {
		return this.gnumParentId;
	}

	public void setGnumParentId(BigDecimal gnumParentId) {
		this.gnumParentId = gnumParentId;
	}

	public BigDecimal getGnumSeatId() {
		return this.gnumSeatId;
	}

	public void setGnumSeatId(BigDecimal gnumSeatId) {
		this.gnumSeatId = gnumSeatId;
	}

	public String getGstrJsFunctionName() {
		return this.gstrJsFunctionName;
	}

	public void setGstrJsFunctionName(String gstrJsFunctionName) {
		this.gstrJsFunctionName = gstrJsFunctionName;
	}

	public String getGstrMenuName() {
		return this.gstrMenuName;
	}

	public void setGstrMenuName(String gstrMenuName) {
		this.gstrMenuName = gstrMenuName;
	}

	public String getGstrMenuclassId() {
		return this.gstrMenuclassId;
	}

	public void setGstrMenuclassId(String gstrMenuclassId) {
		this.gstrMenuclassId = gstrMenuclassId;
	}

	public String getGstrUrl() {
		return this.gstrUrl;
	}

	public void setGstrUrl(String gstrUrl) {
		this.gstrUrl = gstrUrl;
	}

}