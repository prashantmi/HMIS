package usermgmt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the gblt_role_mst database table.
 * 
 */
@Entity
@Table(name="gblt_role_mst")
@NamedQuery(name="GbltRoleMst.findAll", query="SELECT g FROM GbltRoleMst g")
public class GbltRoleMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GbltRoleMstPK id;

	@Column(name="gbl_isvalid")
	private BigDecimal gblIsvalid;

	@Column(name="gdt_effect_date")
	private Timestamp gdtEffectDate;

	@Column(name="gdt_entry_date")
	private Timestamp gdtEntryDate;

	@Column(name="gnum_module_id")
	private BigDecimal gnumModuleId;

	@Column(name="gnum_seat_id")
	private BigDecimal gnumSeatId;

	@Column(name="gstr_role_name")
	private String gstrRoleName;

	public GbltRoleMst() {
	}

	public GbltRoleMstPK getId() {
		return this.id;
	}

	public void setId(GbltRoleMstPK id) {
		this.id = id;
	}

	public BigDecimal getGblIsvalid() {
		return this.gblIsvalid;
	}

	public void setGblIsvalid(BigDecimal gblIsvalid) {
		this.gblIsvalid = gblIsvalid;
	}

	public Timestamp getGdtEffectDate() {
		return this.gdtEffectDate;
	}

	public void setGdtEffectDate(Timestamp gdtEffectDate) {
		this.gdtEffectDate = gdtEffectDate;
	}

	public Timestamp getGdtEntryDate() {
		return this.gdtEntryDate;
	}

	public void setGdtEntryDate(Timestamp gdtEntryDate) {
		this.gdtEntryDate = gdtEntryDate;
	}

	public BigDecimal getGnumModuleId() {
		return this.gnumModuleId;
	}

	public void setGnumModuleId(BigDecimal gnumModuleId) {
		this.gnumModuleId = gnumModuleId;
	}

	public BigDecimal getGnumSeatId() {
		return this.gnumSeatId;
	}

	public void setGnumSeatId(BigDecimal gnumSeatId) {
		this.gnumSeatId = gnumSeatId;
	}

	public String getGstrRoleName() {
		return this.gstrRoleName;
	}

	public void setGstrRoleName(String gstrRoleName) {
		this.gstrRoleName = gstrRoleName;
	}

}