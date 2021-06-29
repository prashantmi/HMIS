package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the GBLT_USER_LOG database table.
 * 
 */
@Entity
@Table(name="GBLT_USER_LOG")
@NamedQuery(name="GbltUserLog.findAll", query="SELECT g FROM GbltUserLog g")
public class GbltUserLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_LOGIN_DATE")
	private Date gdtLoginDate;

	@Temporal(TemporalType.DATE)
	@Column(name="GDT_LOGUTT_DATE")
	private Date gdtLoguttDate;

	@Column(name="GNUM_HOSPITAL_CODE")
	private BigDecimal gnumHospitalCode;

	@Column(name="GNUM_SEAT_ID")
	private BigDecimal gnumSeatId;

	@Column(name="GNUM_USERID")
	private BigDecimal gnumUserid;

	@Column(name="GSTR_IP_NUMBER")
	private String gstrIpNumber;

	public GbltUserLog() {
	}

	public Date getGdtLoginDate() {
		return this.gdtLoginDate;
	}

	public void setGdtLoginDate(Date gdtLoginDate) {
		this.gdtLoginDate = gdtLoginDate;
	}

	public Date getGdtLoguttDate() {
		return this.gdtLoguttDate;
	}

	public void setGdtLoguttDate(Date gdtLoguttDate) {
		this.gdtLoguttDate = gdtLoguttDate;
	}

	public BigDecimal getGnumHospitalCode() {
		return this.gnumHospitalCode;
	}

	public void setGnumHospitalCode(BigDecimal gnumHospitalCode) {
		this.gnumHospitalCode = gnumHospitalCode;
	}

	public BigDecimal getGnumSeatId() {
		return this.gnumSeatId;
	}

	public void setGnumSeatId(BigDecimal gnumSeatId) {
		this.gnumSeatId = gnumSeatId;
	}

	public BigDecimal getGnumUserid() {
		return this.gnumUserid;
	}

	public void setGnumUserid(BigDecimal gnumUserid) {
		this.gnumUserid = gnumUserid;
	}

	public String getGstrIpNumber() {
		return this.gstrIpNumber;
	}

	public void setGstrIpNumber(String gstrIpNumber) {
		this.gstrIpNumber = gstrIpNumber;
	}

}