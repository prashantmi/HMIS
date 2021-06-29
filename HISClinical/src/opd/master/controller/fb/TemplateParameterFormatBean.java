package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.lang.reflect.Method;

import hisglobal.exceptions.HisException;

public class TemplateParameterFormatBean
{
	private String bold="false";
	private String italic="false";
	private String underlined="false";
	private String color="";
	private String paraValue="";
	private String paraOptions="";
	private String align="center";
	private String maxlength="";
	private String validationFunction="";
	private String isCompulsory="";
	private String isRange="";
	private String defaultValue="";
	private String regularExpression="";
	private String format="";


	static String ctrlParasNormal[][]=	{ 	{"bold","italic","underlined","color","align"},
			{"bold","italic","underlined","color","maxlength","isCompulsory","defaultValue","validationFunction","format","regularExpression","isRange"},
			{"bold","italic","underlined","color","isCompulsory","defaultValue","validationFunction"},
			{"bold","italic","underlined","color","defaultValue","isCompulsory"},
			{"bold","italic","underlined","color","defaultValue","isCompulsory"},
			{"bold","italic","underlined","color","defaultValue","isCompulsory"},
			{"bold","italic","underlined","color","defaultValue","isCompulsory"}
		};

	static String ctrlParasMatrix[][]=	{ 	{"bold","italic","underlined","color","align"},
		{"maxlength","isCompulsory","defaultValue","validationFunction","format","regularExpression","isRange"},
		{"isCompulsory","defaultValue","validationFunction"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"}
	};
	
	static String ctrlParasConsent[][]=	{ 	{"bold","italic","underlined","color","align"},
		{"maxlength","isCompulsory","defaultValue","validationFunction","format","regularExpression","isRange"},
		{"isCompulsory","defaultValue","validationFunction"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"},
		{"defaultValue","isCompulsory"}
	};
	
	
	public TemplateParameterFormatBean(int tempType,int type,String propList)
	{
		String values[]=propList.split("&");
		Method method[]= this.getClass().getMethods();
		String paraArrayMat[][]=null;
		switch(tempType)
		{
			case 1:
				paraArrayMat=TemplateParameterFormatBean.ctrlParasNormal;
				break;
			case 2:
				paraArrayMat=TemplateParameterFormatBean.ctrlParasMatrix;
				break;
			case 3:
				paraArrayMat=TemplateParameterFormatBean.ctrlParasConsent;
				break;
		}
		for(int i=0;i<paraArrayMat[type-1].length;i++)
		{
			String name= paraArrayMat[type-1][i];
			char ch=(char) (name.charAt(0));
			ch-=32;
			name=name.substring(1,name.length());
			name=String.valueOf(ch)+name;
			
			try
			{
				for(int j=0;j<method.length;j++)
					if(method[j].getName().equals("set"+name))
						method[j].invoke(this, new Object[]{values[i]});
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new HisException("Setting Control Properties with Fetch Methods::"+e);
			}
		}
	}

	public void reset() 
	{
		
	}
	
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getBold() {
		return bold;
	}
	public void setBold(String bold) {
		this.bold = bold;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getItalic() {
		return italic;
	}
	public void setItalic(String italic) {
		this.italic = italic;
	}
	public String getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getUnderlined() {
		return underlined;
	}
	public void setUnderlined(String underlined) {
		this.underlined = underlined;
	}
	public String getValidationFunction() {
		return validationFunction;
	}
	public void setValidationFunction(String validationFunction) {
		this.validationFunction = validationFunction;
	}
	public String getParaOptions() {
		return paraOptions;
	}
	public void setParaOptions(String paraOptions) {
		this.paraOptions = paraOptions;
	}
	public String getIsCompulsory() {
		return isCompulsory;
	}
	public void setIsCompulsory(String isCompulsory) {
		this.isCompulsory = isCompulsory;
	}
	public String getIsRange() {
		return isRange;
	}
	public void setIsRange(String isRange) {
		this.isRange = isRange;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getRegularExpression() {
		return regularExpression.replace("\\","\\\\");
	}
	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}