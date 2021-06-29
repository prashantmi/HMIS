package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GenericMacroPopupFB extends ActionForm
{
	private String hmode;
	private String macroProcessId;
	private String macroTargetFunction;
	private String macroHead;
	private String unitCode;

	public GenericMacroPopupFB()
	{
		this.macroProcessId = "";
		this.macroTargetFunction = "";
		this.macroHead = "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.macroHead = "";
	}

	public String getMacroProcessId()
	{
		return macroProcessId;
	}

	public void setMacroProcessId(String macroProcessId)
	{
		this.macroProcessId = macroProcessId;
	}

	public String getMacroTargetFunction()
	{
		return macroTargetFunction;
	}

	public void setMacroTargetFunction(String macroTargetFunction)
	{
		this.macroTargetFunction = macroTargetFunction;
	}

	public String getMacroHead()
	{
		return macroHead;
	}

	public void setMacroHead(String macroHead)
	{
		this.macroHead = macroHead;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}
}
