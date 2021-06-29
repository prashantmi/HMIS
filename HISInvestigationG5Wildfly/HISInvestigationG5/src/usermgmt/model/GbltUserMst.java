package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GBLT_USER_MST database table.
 * 
 */
@Entity
@Table(name="GBLT_USER_MST")
@NamedQuery(name="GbltUserMst.findAll", query="SELECT g FROM GbltUserMst g")
public class GbltUserMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GNUM_USERID")
	private long gnumUserid;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_CHANGEPASSWORD_DATE")
	private Date gdtChangepasswordDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_EFFECT_DATE")
	private Date gdtEffectDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_ENTRY_DATE")
	private Date gdtEntryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_EXPIRY_DATE")
	private Date gdtExpiryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_LSTMOD_DATE")
	private Date gdtLstmodDate;

	@Column(name="GNUM_DESIGNATION")
	private String gnumDesignation;

	@Column(name="GNUM_ISLOCK")
	private BigDecimal gnumIslock;

	@Column(name="GNUM_ISVALID")
	private BigDecimal gnumIsvalid;

	@Column(name="GNUM_LSTMOD_SEATID")
	private BigDecimal gnumLstmodSeatid;

	@Column(name="GNUM_MOBILE_NUMBER")
	private BigDecimal gnumMobileNumber;

	@Column(name="GNUM_QUESTION_ID")
	private BigDecimal gnumQuestionId;

	@Column(name="GNUM_SEAT_ID")
	private BigDecimal gnumSeatId;

	@Column(name="GNUM_SWAPCARD_NUMBER")
	private String gnumSwapcardNumber;

	@Column(name="GNUM_USER_SEATID")
	private BigDecimal gnumUserSeatid;

	@Column(name="GNUM_USER_TYPE")
	private BigDecimal gnumUserType;

	@Column(name="GNUM_USERLEVEL")
	private BigDecimal gnumUserlevel;

	@Column(name="GSTR_EMAIL_ID")
	private String gstrEmailId;

	@Column(name="GSTR_HINT_ANSWER")
	private String gstrHintAnswer;

	@Column(name="GSTR_OLD_PASSWORD")
	private String gstrOldPassword;

	@Column(name="GSTR_PASSWORD")
	private String gstrPassword;

	@Column(name="GSTR_STATUS_CODE")
	private String gstrStatusCode;

	@Column(name="GSTR_USER_NAME")
	private String gstrUserName;

	@Column(name="GSTR_USR_NAME")
	private String gstrUsrName;

	@Column(name="PSRSTR_EMP_NO")
	private String psrstrEmpNo;

	//uni-directional many-to-one association to GbltHospitalMst
	@ManyToOne
	@JoinColumn(name="GNUM_HOSPITAL_CODE")
	private GbltHospitalMst gbltHospitalMst;

	public GbltUserMst() {
	}

	public long getGnumUserid() {
		return this.gnumUserid;
	}

	public void setGnumUserid(long gnumUserid) {
		this.gnumUserid = gnumUserid;
	}

	public Date getGdtChangepasswordDate() {
		return this.gdtChangepasswordDate;
	}

	public void setGdtChangepasswordDate(Date gdtChangepasswordDate) {
		this.gdtChangepasswordDate = gdtChangepasswordDate;
	}

	public Date getGdtEffectDate() {
		return this.gdtEffectDate;
	}

	public void setGdtEffectDate(Date gdtEffectDate) {
		this.gdtEffectDate = gdtEffectDate;
	}

	public Date getGdtEntryDate() {
		return this.gdtEntryDate;
	}

	public void setGdtEntryDate(Date gdtEntryDate) {
		this.gdtEntryDate = gdtEntryDate;
	}

	public Date getGdtExpiryDate() {
		return this.gdtExpiryDate;
	}

	public void setGdtExpiryDate(Date gdtExpiryDate) {
		this.gdtExpiryDate = gdtExpiryDate;
	}

	public Date getGdtLstmodDate() {
		return this.gdtLstmodDate;
	}

	public void setGdtLstmodDate(Date gdtLstmodDate) {
		this.gdtLstmodDate = gdtLstmodDate;
	}

	public String getGnumDesignation() {
		return this.gnumDesignation;
	}

	public void setGnumDesignation(String gnumDesignation) {
		this.gnumDesignation = gnumDesignation;
	}

	public BigDecimal getGnumIslock() {
		return this.gnumIslock;
	}

	public void setGnumIslock(BigDecimal gnumIslock) {
		this.gnumIslock = gnumIslock;
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

	public BigDecimal getGnumMobileNumber() {
		return this.gnumMobileNumber;
	}

	public void setGnumMobileNumber(BigDecimal gnumMobileNumber) {
		this.gnumMobileNumber = gnumMobileNumber;
	}

	public BigDecimal getGnumQuestionId() {
		return this.gnumQuestionId;
	}

	public void setGnumQuestionId(BigDecimal gnumQuestionId) {
		this.gnumQuestionId = gnumQuestionId;
	}

	public BigDecimal getGnumSeatId() {
		return this.gnumSeatId;
	}

	public void setGnumSeatId(BigDecimal gnumSeatId) {
		this.gnumSeatId = gnumSeatId;
	}

	public String getGnumSwapcardNumber() {
		return this.gnumSwapcardNumber;
	}

	public void setGnumSwapcardNumber(String gnumSwapcardNumber) {
		this.gnumSwapcardNumber = gnumSwapcardNumber;
	}

	public BigDecimal getGnumUserSeatid() {
		return this.gnumUserSeatid;
	}

	public void setGnumUserSeatid(BigDecimal gnumUserSeatid) {
		this.gnumUserSeatid = gnumUserSeatid;
	}

	public BigDecimal getGnumUserType() {
		return this.gnumUserType;
	}

	public void setGnumUserType(BigDecimal gnumUserType) {
		this.gnumUserType = gnumUserType;
	}

	public BigDecimal getGnumUserlevel() {
		return this.gnumUserlevel;
	}

	public void setGnumUserlevel(BigDecimal gnumUserlevel) {
		this.gnumUserlevel = gnumUserlevel;
	}

	public String getGstrEmailId() {
		return this.gstrEmailId;
	}

	public void setGstrEmailId(String gstrEmailId) {
		this.gstrEmailId = gstrEmailId;
	}

	public String getGstrHintAnswer() {
		return this.gstrHintAnswer;
	}

	public void setGstrHintAnswer(String gstrHintAnswer) {
		this.gstrHintAnswer = gstrHintAnswer;
	}

	public String getGstrOldPassword() {
		return this.gstrOldPassword;
	}

	public void setGstrOldPassword(String gstrOldPassword) {
		this.gstrOldPassword = gstrOldPassword;
	}

	public String getGstrPassword() {
		return this.gstrPassword;
	}

	public void setGstrPassword(String gstrPassword) {
		this.gstrPassword = gstrPassword;
	}

	public String getGstrStatusCode() {
		return this.gstrStatusCode;
	}

	public void setGstrStatusCode(String gstrStatusCode) {
		this.gstrStatusCode = gstrStatusCode;
	}

	public String getGstrUserName() {
		return this.gstrUserName;
	}

	public void setGstrUserName(String gstrUserName) {
		this.gstrUserName = gstrUserName;
	}

	public String getGstrUsrName() {
		return this.gstrUsrName;
	}

	public void setGstrUsrName(String gstrUsrName) {
		this.gstrUsrName = gstrUsrName;
	}

	public String getPsrstrEmpNo() {
		return this.psrstrEmpNo;
	}

	public void setPsrstrEmpNo(String psrstrEmpNo) {
		this.psrstrEmpNo = psrstrEmpNo;
	}

	public GbltHospitalMst getGbltHospitalMst() {
		return this.gbltHospitalMst;
	}

	public void setGbltHospitalMst(GbltHospitalMst gbltHospitalMst) {
		this.gbltHospitalMst = gbltHospitalMst;
	}

}