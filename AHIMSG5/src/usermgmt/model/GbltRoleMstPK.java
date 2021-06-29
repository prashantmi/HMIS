package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the gblt_role_mst database table.
 * 
 */
@Embeddable
public class GbltRoleMstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="gnum_role_id")
	private long gnumRoleId;

	@Column(name="gnum_hospital_code")
	private long gnumHospitalCode;

	public GbltRoleMstPK() {
	}
	public long getGnumRoleId() {
		return this.gnumRoleId;
	}
	public void setGnumRoleId(long gnumRoleId) {
		this.gnumRoleId = gnumRoleId;
	}
	public long getGnumHospitalCode() {
		return this.gnumHospitalCode;
	}
	public void setGnumHospitalCode(long gnumHospitalCode) {
		this.gnumHospitalCode = gnumHospitalCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GbltRoleMstPK)) {
			return false;
		}
		GbltRoleMstPK castOther = (GbltRoleMstPK)other;
		return 
			(this.gnumRoleId == castOther.gnumRoleId)
			&& (this.gnumHospitalCode == castOther.gnumHospitalCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.gnumRoleId ^ (this.gnumRoleId >>> 32)));
		hash = hash * prime + ((int) (this.gnumHospitalCode ^ (this.gnumHospitalCode >>> 32)));
		
		return hash;
	}
}