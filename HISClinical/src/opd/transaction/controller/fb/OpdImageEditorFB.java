package opd.transaction.controller.fb;

/**
 * @author  CDAC
 */

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdImageEditorFB extends ActionForm
{
	private String hmode;
	private String inFileName;
	private String outFileName;
	private String inFilePath;
	private String outFilePath;
	
	private String title;
	private String controlDesc;
	private String colorDesc;
	
	public OpdImageEditorFB() 
	{
		this.hmode="";
		this.inFileName="";
		this.inFilePath="";
		this.outFileName="";
		this.outFilePath="";
		this.title="";
		this.colorDesc="";
		this.controlDesc="";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		super.reset(mapping,request);
		this.hmode="";
		this.hmode="";
		this.inFileName="";
		this.inFilePath="";
		this.outFileName="";
		this.outFilePath="";
		this.title="";
		this.colorDesc="";
		this.controlDesc="";
	}

	public String getInFileName() {
		return inFileName;
	}

	public void setInFileName(String inFileName) {
		this.inFileName = inFileName;
	}

	public String getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

	public String getInFilePath() {
		return inFilePath;
	}

	public void setInFilePath(String inFilePath) {
		this.inFilePath = inFilePath;
	}

	public String getOutFilePath() {
		return outFilePath;
	}

	public void setOutFilePath(String outFilePath) {
		this.outFilePath = outFilePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getControlDesc() {
		return controlDesc;
	}

	public void setControlDesc(String controlDesc) {
		this.controlDesc = controlDesc;
	}

	public String getColorDesc() {
		return colorDesc;
	}

	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}

	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
}
