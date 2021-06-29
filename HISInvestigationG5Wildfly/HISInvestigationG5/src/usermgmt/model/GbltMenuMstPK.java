package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the GBLT_MENU_MST database table.
 * 
 */
@Embeddable
public class GbltMenuMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="GNUM_MENU_ID")
	private long gnumMenuId;

	@Column(name="GNUM_HOSPITAL_CODE")
	private long gnumHospitalCode;

	@Column(name="GNUM_SLNO")
	private long gnumSlno;

	public GbltMenuMstPK() {
	}
	public long getGnumMenuId() {
		return this.gnumMenuId;
	}
	public void setGnumMenuId(long gnumMenuId) {
		this.gnumMenuId = gnumMenuId;
	}
	public long getGnumHospitalCode() {
		return this.gnumHospitalCode;
	}
	public void setGnumHospitalCode(long gnumHospitalCode) {
		this.gnumHospitalCode = gnumHospitalCode;
	}
	public long getGnumSlno() {
		return this.gnumSlno;
	}
	public void setGnumSlno(long gnumSlno) {
		this.gnumSlno = gnumSlno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GbltMenuMstPK)) {
			return false;
		}
		GbltMenuMstPK castOther = (GbltMenuMstPK)other;
		return 
			(this.gnumMenuId == castOther.gnumMenuId)
			&& (this.gnumHospitalCode == castOther.gnumHospitalCode)
			&& (this.gnumSlno == castOther.gnumSlno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.gnumMenuId ^ (this.gnumMenuId >>> 32)));
		hash = hash * prime + ((int) (this.gnumHospitalCode ^ (this.gnumHospitalCode >>> 32)));
		hash = hash * prime + ((int) (this.gnumSlno ^ (this.gnumSlno >>> 32)));
		
		return hash;
	}
}