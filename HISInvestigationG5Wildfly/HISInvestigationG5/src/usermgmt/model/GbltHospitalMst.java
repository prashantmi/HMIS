package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GBLT_HOSPITAL_MST database table.
 * 
 */
@Entity
@Table(name="GBLT_HOSPITAL_MST")
@NamedQuery(name="GbltHospitalMst.findAll", query="SELECT g FROM GbltHospitalMst g")
public class GbltHospitalMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GNUM_HOSPITAL_CODE")
	private long gnumHospitalCode;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_ENTRY_DATE")
	private Date gdtEntryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_LSTMOD_DATE")
	private Date gdtLstmodDate;

	@Column(name="GNUM_ACTIVE")
	private BigDecimal gnumActive;

	@Column(name="GNUM_HL7_CODE")
	private BigDecimal gnumHl7Code;

	@Column(name="GNUM_HOSPITAL_CATG")
	private BigDecimal gnumHospitalCatg;

	@Column(name="GNUM_HOSPITAL_TYPE")
	private BigDecimal gnumHospitalType;

	@Column(name="GNUM_IS_ASSOCIATED")
	private BigDecimal gnumIsAssociated;

	@Column(name="GNUM_ISVALID")
	private BigDecimal gnumIsvalid;

	@Column(name="GNUM_LSTMOD_SEATID")
	private BigDecimal gnumLstmodSeatid;

	@Column(name="GNUM_PIN_CODE")
	private BigDecimal gnumPinCode;

	@Column(name="GNUM_SEAT_ID")
	private BigDecimal gnumSeatId;

	@Column(name="GNUM_STATE_CODE")
	private BigDecimal gnumStateCode;

	@Column(name="GSTR_BED_CAPACITY")
	private String gstrBedCapacity;

	@Column(name="GSTR_BUSROUTES_NO")
	private String gstrBusroutesNo;

	@Column(name="GSTR_CITY")
	private String gstrCity;

	@Column(name="GSTR_CONTACT_PERSON")
	private String gstrContactPerson;

	@Column(name="GSTR_EMAIL")
	private String gstrEmail;

	@Column(name="GSTR_FAX")
	private String gstrFax;

	@Column(name="GSTR_HOSP_SHORT_NAME")
	private String gstrHospShortName;

	@Column(name="GSTR_HOSPITAL_ADD1")
	private String gstrHospitalAdd1;

	@Column(name="GSTR_HOSPITAL_ADD2")
	private String gstrHospitalAdd2;

	@Column(name="GSTR_HOSPITAL_NAME")
	private String gstrHospitalName;

	@Column(name="GSTR_LUNCH_BREAK")
	private String gstrLunchBreak;

	@Column(name="GSTR_PAN_NO")
	private String gstrPanNo;

	@Column(name="GSTR_PASSWORD")
	private String gstrPassword;

	@Column(name="GSTR_PHONE")
	private String gstrPhone;

	@Column(name="GSTR_REMARKS")
	private String gstrRemarks;

	@Column(name="GSTR_SAT_TIMING")
	private String gstrSatTiming;

	@Column(name="GSTR_TAN_NO")
	private String gstrTanNo;

	@Column(name="GSTR_USER_NAME")
	private String gstrUserName;

	@Column(name="GSTR_WEEKDAYS_TIMING")
	private String gstrWeekdaysTiming;

	@Column(name="HIPNUM_ORG_TYPE")
	private BigDecimal hipnumOrgType;

	public GbltHospitalMst() {
	}

	public long getGnumHospitalCode() {
		return this.gnumHospitalCode;
	}

	public void setGnumHospitalCode(long gnumHospitalCode) {
		this.gnumHospitalCode = gnumHospitalCode;
	}

	public Date getGdtEntryDate() {
		return this.gdtEntryDate;
	}

	public void setGdtEntryDate(Date gdtEntryDate) {
		this.gdtEntryDate = gdtEntryDate;
	}

	public Date getGdtLstmodDate() {
		return this.gdtLstmodDate;
	}

	public void setGdtLstmodDate(Date gdtLstmodDate) {
		this.gdtLstmodDate = gdtLstmodDate;
	}

	public BigDecimal getGnumActive() {
		return this.gnumActive;
	}

	public void setGnumActive(BigDecimal gnumActive) {
		this.gnumActive = gnumActive;
	}

	public BigDecimal getGnumHl7Code() {
		return this.gnumHl7Code;
	}

	public void setGnumHl7Code(BigDecimal gnumHl7Code) {
		this.gnumHl7Code = gnumHl7Code;
	}

	public BigDecimal getGnumHospitalCatg() {
		return this.gnumHospitalCatg;
	}

	public void setGnumHospitalCatg(BigDecimal gnumHospitalCatg) {
		this.gnumHospitalCatg = gnumHospitalCatg;
	}

	public BigDecimal getGnumHospitalType() {
		return this.gnumHospitalType;
	}

	public void setGnumHospitalType(BigDecimal gnumHospitalType) {
		this.gnumHospitalType = gnumHospitalType;
	}

	public BigDecimal getGnumIsAssociated() {
		return this.gnumIsAssociated;
	}

	public void setGnumIsAssociated(BigDecimal gnumIsAssociated) {
		this.gnumIsAssociated = gnumIsAssociated;
	}

	public BigDecimal getGnumIsvalid() {
		return this.gnumIsvalid;
	}

	public void setGnumIsvalid(BigDecimal gnumIsvalid) {
		this.gnumIsvalid = gnumIsvalid;
	}

	public BigDecimal getGnumLstmodSeatid() {
		return this.gnumLstmodSeatid;
	}

	public void setGnumLstmodSeatid(BigDecimal gnumLstmodSeatid) {
		this.gnumLstmodSeatid = gnumLstmodSeatid;
	}

	public BigDecimal getGnumPinCode() {
		return this.gnumPinCode;
	}

	public void setGnumPinCode(BigDecimal gnumPinCode) {
		this.gnumPinCode = gnumPinCode;
	}

	public BigDecimal getGnumSeatId() {
		return this.gnumSeatId;
	}

	public void setGnumSeatId(BigDecimal gnumSeatId) {
		this.gnumSeatId = gnumSeatId;
	}

	public BigDecimal getGnumStateCode() {
		return this.gnumStateCode;
	}

	public void setGnumStateCode(BigDecimal gnumStateCode) {
		this.gnumStateCode = gnumStateCode;
	}

	public String getGstrBedCapacity() {
		return this.gstrBedCapacity;
	}

	public void setGstrBedCapacity(String gstrBedCapacity) {
		this.gstrBedCapacity = gstrBedCapacity;
	}

	public String getGstrBusroutesNo() {
		return this.gstrBusroutesNo;
	}

	public void setGstrBusroutesNo(String gstrBusroutesNo) {
		this.gstrBusroutesNo = gstrBusroutesNo;
	}

	public String getGstrCity() {
		return this.gstrCity;
	}

	public void setGstrCity(String gstrCity) {
		this.gstrCity = gstrCity;
	}

	public String getGstrContactPerson() {
		return this.gstrContactPerson;
	}

	public void setGstrContactPerson(String gstrContactPerson) {
		this.gstrContactPerson = gstrContactPerson;
	}

	public String getGstrEmail() {
		return this.gstrEmail;
	}

	public void setGstrEmail(String gstrEmail) {
		this.gstrEmail = gstrEmail;
	}

	public String getGstrFax() {
		return this.gstrFax;
	}

	public void setGstrFax(String gstrFax) {
		this.gstrFax = gstrFax;
	}

	public String getGstrHospShortName() {
		return this.gstrHospShortName;
	}

	public void setGstrHospShortName(String gstrHospShortName) {
		this.gstrHospShortName = gstrHospShortName;
	}

	public String getGstrHospitalAdd1() {
		return this.gstrHospitalAdd1;
	}

	public void setGstrHospitalAdd1(String gstrHospitalAdd1) {
		this.gstrHospitalAdd1 = gstrHospitalAdd1;
	}

	public String getGstrHospitalAdd2() {
		return this.gstrHospitalAdd2;
	}

	public void setGstrHospitalAdd2(String gstrHospitalAdd2) {
		this.gstrHospitalAdd2 = gstrHospitalAdd2;
	}

	public String getGstrHospitalName() {
		return this.gstrHospitalName;
	}

	public void setGstrHospitalName(String gstrHospitalName) {
		this.gstrHospitalName = gstrHospitalName;
	}

	public String getGstrLunchBreak() {
		return this.gstrLunchBreak;
	}

	public void setGstrLunchBreak(String gstrLunchBreak) {
		this.gstrLunchBreak = gstrLunchBreak;
	}

	public String getGstrPanNo() {
		return this.gstrPanNo;
	}

	public void setGstrPanNo(String gstrPanNo) {
		this.gstrPanNo = gstrPanNo;
	}

	public String getGstrPassword() {
		return this.gstrPassword;
	}

	public void setGstrPassword(String gstrPassword) {
		this.gstrPassword = gstrPassword;
	}

	public String getGstrPhone() {
		return this.gstrPhone;
	}

	public void setGstrPhone(String gstrPhone) {
		this.gstrPhone = gstrPhone;
	}

	public String getGstrRemarks() {
		return this.gstrRemarks;
	}

	public void setGstrRemarks(String gstrRemarks) {
		this.gstrRemarks = gstrRemarks;
	}

	public String getGstrSatTiming() {
		return this.gstrSatTiming;
	}

	public void setGstrSatTiming(String gstrSatTiming) {
		this.gstrSatTiming = gstrSatTiming;
	}

	public String getGstrTanNo() {
		return this.gstrTanNo;
	}

	public void setGstrTanNo(String gstrTanNo) {
		this.gstrTanNo = gstrTanNo;
	}

	public String getGstrUserName() {
		return this.gstrUserName;
	}

	public void setGstrUserName(String gstrUserName) {
		this.gstrUserName = gstrUserName;
	}

	public String getGstrWeekdaysTiming() {
		return this.gstrWeekdaysTiming;
	}

	public void setGstrWeekdaysTiming(String gstrWeekdaysTiming) {
		this.gstrWeekdaysTiming = gstrWeekdaysTiming;
	}

	public BigDecimal getHipnumOrgType() {
		return this.hipnumOrgType;
	}

	public void setHipnumOrgType(BigDecimal hipnumOrgType) {
		this.hipnumOrgType = hipnumOrgType;
	}

}