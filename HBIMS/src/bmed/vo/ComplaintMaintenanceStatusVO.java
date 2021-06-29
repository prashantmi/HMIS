package bmed.vo;

import java.util.ArrayList;

public class ComplaintMaintenanceStatusVO {
	
	private String strMode;
	private ArrayList<?> listDepartmentOptions=null;
	private String strHospitalCode;
	/**
	 * @return the listDepartmentOptions
	 */
	public ArrayList<?> getListDepartmentOptions() {
		return listDepartmentOptions;
	}
	/**
	 * @param listDepartmentOptions the listDepartmentOptions to set
	 */
	public void setListDepartmentOptions(ArrayList<?> listDepartmentOptions) {
		this.listDepartmentOptions = listDepartmentOptions;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	

}
