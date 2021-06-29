/*Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate Generation Vo
 ## Purpose						:Certificate Generate Process
 ## Date of Creation			:02-Dec-2014 

*/


package mrd.vo;

import hisglobal.vo.ValueObject;

public class PackageServiceMstVO extends ValueObject{
private String tariffName;
private String tarrifId;
private String quantity;
private String amount;
private String pkgName;

public String getTariffName() {
	return tariffName;
}
public void setTariffName(String tariffName) {
	this.tariffName = tariffName;
}
public String getTarrifId() {
	return tarrifId;
}
public void setTarrifId(String tarrifId) {
	this.tarrifId = tarrifId;
}
public String getQuantity() {
	return quantity;
}
public void setQuantity(String quantity) {
	this.quantity = quantity;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getPkgName() {
	return pkgName;
}
public void setPkgName(String pkgName) {
	this.pkgName = pkgName;
}
}
