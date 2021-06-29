package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UnitEpisodeKeywordFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String controls[];
	private String deptUnitCode;
	private String deptCode;
	private String deptUnitName;
	private String[] unselectedKeyword;
	private String[] selectedKeyword;
	private String[] selectedUnit;
	private String[] unselectedUnit;
	private String keywordCode;
	private String keywordName;
	private String slNo;

	private ArrayList unitsList;
	private ArrayList mainUnitsList;

	public UnitEpisodeKeywordFB()
	{
		this.hmode = "";
		this.controls = new String[2];

		this.deptCode = "-1";
		this.deptUnitCode = "-1";
		this.unselectedKeyword = null;
		this.selectedKeyword = null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.deptCode = "-1";
		this.deptUnitCode = "-1";
		this.unselectedKeyword = null;
		this.selectedKeyword = null;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String[] getControls()
	{
		return controls;
	}

	public void setControls(String[] controls)
	{
		this.controls = controls;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getDeptCode()
	{
		return deptCode;
	}

	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}

	public String getDeptUnitName()
	{
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName)
	{
		this.deptUnitName = deptUnitName;
	}

	public String[] getUnselectedKeyword()
	{
		return unselectedKeyword;
	}

	public void setUnselectedKeyword(String[] unselectedKeyword)
	{
		this.unselectedKeyword = unselectedKeyword;
	}

	public String[] getSelectedKeyword()
	{
		return selectedKeyword;
	}

	public void setSelectedKeyword(String[] selectedKeyword)
	{
		this.selectedKeyword = selectedKeyword;
	}

	public String[] getSelectedUnit()
	{
		return selectedUnit;
	}

	public void setSelectedUnit(String[] selectedUnit)
	{
		this.selectedUnit = selectedUnit;
	}

	public String[] getUnselectedUnit()
	{
		return unselectedUnit;
	}

	public void setUnselectedUnit(String[] unselectedUnit)
	{
		this.unselectedUnit = unselectedUnit;
	}

	public String getKeywordCode()
	{
		return keywordCode;
	}

	public void setKeywordCode(String keywordCode)
	{
		this.keywordCode = keywordCode;
	}

	public String getKeywordName()
	{
		return keywordName;
	}

	public void setKeywordName(String keywordName)
	{
		this.keywordName = keywordName;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public ArrayList getUnitsList()
	{
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList)
	{
		this.unitsList = unitsList;
	}

	public ArrayList getMainUnitsList()
	{
		return mainUnitsList;
	}

	public void setMainUnitsList(ArrayList mainUnitsList)
	{
		this.mainUnitsList = mainUnitsList;
	}
}
