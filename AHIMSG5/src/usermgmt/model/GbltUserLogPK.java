package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gblt_user_log database table.
 * 
 */
@Embeddable
public class GbltUserLogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="gnum_userid", insertable=false, updatable=false)
	private long gnumUserid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gdt_login_date")
	private java.util.Date gdtLoginDate;

	public GbltUserLogPK() {
	}
	public long getGnumUserid() {
		return this.gnumUserid;
	}
	public void setGnumUserid(long gnumUserid) {
		this.gnumUserid = gnumUserid;
	}
	public java.util.Date getGdtLoginDate() {
		return this.gdtLoginDate;
	}
	public void setGdtLoginDate(java.util.Date gdtLoginDate) {
		this.gdtLoginDate = gdtLoginDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GbltUserLogPK)) {
			return false;
		}
		GbltUserLogPK castOther = (GbltUserLogPK)other;
		return 
			(this.gnumUserid == castOther.gnumUserid)
			&& this.gdtLoginDate.equals(castOther.gdtLoginDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.gnumUserid ^ (this.gnumUserid >>> 32)));
		hash = hash * prime + this.gdtLoginDate.hashCode();
		
		return hash;
	}
}