package opd.transaction.controller.fb;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class OpdDeskFooterFB extends CRNoFB {
	private String episodeIsOpen;
	private String episodeNextVisitDate;
	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}
	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}
	public String getEpisodeNextVisitDate() {
		return episodeNextVisitDate;
	}
	public void setEpisodeNextVisitDate(String episodeNextVisitDate) {
		this.episodeNextVisitDate = episodeNextVisitDate;
	}
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		this.setEpisodeIsOpen("");
		this.setEpisodeNextVisitDate("");
		super.reset(arg0, arg1);
	}
}
