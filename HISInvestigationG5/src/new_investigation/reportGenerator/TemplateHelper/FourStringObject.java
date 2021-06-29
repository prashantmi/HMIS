 
 
package new_investigation.reportGenerator.TemplateHelper;

import new_investigation.reportGenerator.TemplateHelper.vo.ValueObject;

 

 


 


import java.io.Serializable;

public class FourStringObject extends ValueObject {
	private String name;
	private String code;
	private String desc;
    private String loincCode;
    private String refRange;
    
	public String getRefRange() {
		return refRange;
	}



	public void setRefRange(String refRange) {
		this.refRange = refRange;
	}
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FourStringObject(String name,String code,String desc, String loinc, String refRange)
	{
		this.name=name;
		this.code=code;
		this.desc=desc;
        this.loincCode = loinc;
        this.refRange = refRange;
	}
	public FourStringObject() {
	
	}

     
    public String getLoincCode() {
        return loincCode;
    }

     
    public void setLoincCode(String loincCode) {
        this.loincCode = loincCode;
    }

}

