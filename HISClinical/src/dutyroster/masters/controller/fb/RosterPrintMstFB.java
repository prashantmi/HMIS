package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterPrintMstFB extends ActionForm {

	private String rosterCategory;
	private String rosterType;
	private String hmode;
    private String hospitalCode;
	private String noOfInstructionRow;
	private String displayInstruction;
	private String displayRosterBy;
	private String displayCopyTo;	
	private String noOfCopyToRow;
	private String alignInstruction;
	private String alignRosterBy;
	private String alignCopyTo;
	private String concatedValueOfInstruction;
	private String concatedValueOfCopyTo;
	private String concatedValueOfRosterBy;
	private String changeSequence;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		
		this.rosterCategory = "";
		this.rosterType = "";
		this.hospitalCode = "";	
		this.noOfInstructionRow="1";
		this.noOfCopyToRow="1";		
		this.displayInstruction="";
		this.displayRosterBy="";
		this.displayCopyTo="";		
		this.alignInstruction="1";
		this.alignRosterBy="2";
		this.alignCopyTo="1";
		this.concatedValueOfInstruction="";
		this.concatedValueOfCopyTo="";
		this.concatedValueOfRosterBy="";
		this.changeSequence="";
				
	}


	
	
	
	
	
	
	
	
	
	
	public String getConcatedValueOfRosterBy() {
		return concatedValueOfRosterBy;
	}


	public void setConcatedValueOfRosterBy(String concatedValueOfRosterBy) {
		this.concatedValueOfRosterBy = concatedValueOfRosterBy;
	}

	public String getNoOfInstructionRow() {
		return noOfInstructionRow;
	}


	public void setNoOfInstructionRow(String noOfInstructionRow) {
		this.noOfInstructionRow = noOfInstructionRow;
	}


	public String getDisplayInstruction() {
		return displayInstruction;
	}




	public void setDisplayInstruction(String displayInstruction) {
		this.displayInstruction = displayInstruction;
	}




	public String getAlignInstruction() {
		return alignInstruction;
	}




	public void setAlignInstruction(String alignInstruction) {
		this.alignInstruction = alignInstruction;
	}




	public String getAlignRosterBy() {
		return alignRosterBy;
	}




	public void setAlignRosterBy(String alignRosterBy) {
		this.alignRosterBy = alignRosterBy;
	}




	public String getAlignCopyTo() {
		return alignCopyTo;
	}




	public void setAlignCopyTo(String alignCopyTo) {
		this.alignCopyTo = alignCopyTo;
	}




	public String getRosterCategory() {
		return rosterCategory;
	}


	public void setRosterCategory(String rosterCategory) {
		this.rosterCategory = rosterCategory;
	}


	public String getRosterType() {
		return rosterType;
	}


	public void setRosterType(String rosterType) {
		this.rosterType = rosterType;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getDisplayRosterBy() {
		return displayRosterBy;
	}

	public void setDisplayRosterBy(String displayRosterBy) {
		this.displayRosterBy = displayRosterBy;
	}
	
	public String getNoOfCopyToRow() {
		return noOfCopyToRow;
	}
	
	public void setNoOfCopyToRow(String noOfCopyToRow) {
		this.noOfCopyToRow = noOfCopyToRow;
	}

	public String getDisplayCopyTo() {
		return displayCopyTo;
	}

	public void setDisplayCopyTo(String displayCopyTo) {
		this.displayCopyTo = displayCopyTo;
	}

	public String getConcatedValueOfInstruction() {
		return concatedValueOfInstruction;
	}

	public void setConcatedValueOfInstruction(String concatedValueOfInstruction) {
		this.concatedValueOfInstruction = concatedValueOfInstruction;
	}

	public String getConcatedValueOfCopyTo() {
		return concatedValueOfCopyTo;
	}

	public void setConcatedValueOfCopyTo(String concatedValueOfCopyTo) {
		this.concatedValueOfCopyTo = concatedValueOfCopyTo;
	}












	public String getChangeSequence() {
		return changeSequence;
	}












	public void setChangeSequence(String changeSequence) {
		this.changeSequence = changeSequence;
	}

	
	
}
