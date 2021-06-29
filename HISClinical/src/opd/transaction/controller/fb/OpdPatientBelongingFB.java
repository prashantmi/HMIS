package opd.transaction.controller.fb;

import registration.controller.fb.CRNoFB;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;



public class OpdPatientBelongingFB extends CRNoFB {
	
	private String hmode;
	private String patCrNo;
	private String belongingItemCode;
	private String quantity;
	private String remarks; 
	private String removeBelongingCode;
	private String[] chk;
	private String selectedMode;
	private String handOverTo="";
	private String relation;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setBelongingItemCode("");
		setQuantity("");
		setRemarks("");
		super.reset(mapping, request);
	}
	

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getHandOverTo() {
		return handOverTo;
	}

	public void setHandOverTo(String handOverTo) {
		this.handOverTo = handOverTo;
	}

	public String getSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
	}

	public String getRemoveBelongingCode() {
		return removeBelongingCode;
	}

	public void setRemoveBelongingCode(String removeBelongingCode) {
		this.removeBelongingCode = removeBelongingCode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getBelongingItemCode() {
		return belongingItemCode;
	}

	public void setBelongingItemCode(String belongingItemCode) {
		this.belongingItemCode = belongingItemCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getPatCrNo()
	{
		return patCrNo;
	}


	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

}
