package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OPDUnitImageMasterFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String controls[];
	private String deptUnitCode;
	private String deptCode;
	private String deptUnitName;
	private String[] unselectedImages;
	private String[] selectedImages;
	private String[] selectedUnit;
	private String[] unselectedUnit;
	private String imageCode;
	private String imageName;
	private String slNo;
	
	private ArrayList unitsList;
	private ArrayList mainUnitsList;

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public OPDUnitImageMasterFB()
	{
		this.hmode = "";
		this.controls= new String[2];
		
		this.deptCode = "-1";
		this.deptUnitCode = "-1";
		this.unselectedImages = null;
		this.selectedImages = null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.deptCode = "-1";
		this.deptUnitCode = "-1";
		this.unselectedImages = null;
		this.selectedImages = null;
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

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String[] getSelectedImages()
	{
		return selectedImages;
	}

	public void setSelectedImages(String[] selectedImages)
	{
		this.selectedImages = selectedImages;
	}

	public String[] getUnselectedImages()
	{
		return unselectedImages;
	}

	public void setUnselectedImages(String[] unselectedImages)
	{
		this.unselectedImages = unselectedImages;
	}

	public String getDeptUnitName()
	{
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName)
	{
		this.deptUnitName = deptUnitName;
	}

	public String getImageCode()
	{
		return imageCode;
	}

	public void setImageCode(String imageCode)
	{
		this.imageCode = imageCode;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String[] getControls()
	{
		return controls;
	}

	public void setControls(String[] controls)
	{
		this.controls = controls;
	}

	public String[] getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}

	public String[] getUnselectedUnit() {
		return unselectedUnit;
	}

	public void setUnselectedUnit(String[] unselectedUnit) {
		this.unselectedUnit = unselectedUnit;
	}

	public ArrayList getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}

	public ArrayList getMainUnitsList() {
		return mainUnitsList;
	}

	public void setMainUnitsList(ArrayList mainUnitsList) {
		this.mainUnitsList = mainUnitsList;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
