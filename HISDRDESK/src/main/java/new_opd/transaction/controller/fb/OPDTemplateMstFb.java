package new_opd.transaction.controller.fb;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class OPDTemplateMstFb extends ActionForm{
	
	
	

	private String strHtmlString;
	private String strModifyData;
	private String strJsonParaMetereIdString;
	private String strDeptValues;
	private String strTemplateName;
	private String[] strDeptCode;
	private String[] chk ;
	private String strEssentilaJson ;
	private String strTemplateType="";
	private String strTempCatValues="";
	private String strTempCatCode="";
	private String strTemplateCatValues;
	private String strTemplateModifyHtml;
	private String strChk="";
	
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrTemplateModifyHtml() {
		return strTemplateModifyHtml;
	}
	public void setStrTemplateModifyHtml(String strTemplateModifyHtml) {
		this.strTemplateModifyHtml = strTemplateModifyHtml;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getStrTemplateCatValues() {
		return strTemplateCatValues;
	}
	public void setStrTemplateCatValues(String strTemplateCatValues) {
		this.strTemplateCatValues = strTemplateCatValues;
	}
	private WebRowSet strTemalteListingData=null;
	private String strTemplateDeleteParm ;
	
	
	public WebRowSet getStrTemalteListingData() {
		return strTemalteListingData;
	}
	public void setStrTemalteListingData(WebRowSet strTemalteListingData) {
		this.strTemalteListingData = strTemalteListingData;
	}
	public String getStrTemplateDeleteParm() {
		return strTemplateDeleteParm;
	}
	public void setStrTemplateDeleteParm(String strTemplateDeleteParm) {
		this.strTemplateDeleteParm = strTemplateDeleteParm;
	}
	public String getStrEssentilaJson() {
		return strEssentilaJson;
	}
	public void setStrEssentilaJson(String strEssentilaJson) {
		this.strEssentilaJson = strEssentilaJson;
	}
	public String[] getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String[] strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrTemplateName() {
		return strTemplateName;
	}
	public void setStrTemplateName(String strTemplateName) {
		this.strTemplateName = strTemplateName;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrHtmlString() {
		return strHtmlString;
	}
	public void setStrHtmlString(String strHtmlString) {
		this.strHtmlString = strHtmlString;
	}
	public String getStrJsonParaMetereIdString() {
		return strJsonParaMetereIdString;
	}
	public void setStrJsonParaMetereIdString(String strJsonParaMetereIdString) {
		this.strJsonParaMetereIdString = strJsonParaMetereIdString;
	}
	public String getStrTemplateType() {
		return strTemplateType;
	}
	public void setStrTemplateType(String strTemplateType) {
		this.strTemplateType = strTemplateType;
	}
	public String getStrTempCatValues() {
		return strTempCatValues;
	}
	public void setStrTempCatValues(String strTempCatValues) {
		this.strTempCatValues = strTempCatValues;
	}
	public String getStrTempCatCode() {
		return strTempCatCode;
	}
	public void setStrTempCatCode(String strTempCatCode) {
		this.strTempCatCode = strTempCatCode;
	}
	
	public String getStrModifyData() {
		return strModifyData;
	}
	public void setStrModifyData(String strModifyData) {
		this.strModifyData = strModifyData;
	}

}
