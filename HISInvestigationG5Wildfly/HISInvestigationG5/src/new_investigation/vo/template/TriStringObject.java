package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

import java.io.Serializable;

public class TriStringObject extends ValueObject {
	private String name;
	private String code;
	private String desc;
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
	public TriStringObject(String name,String code,String desc)
	{
		this.name=name;
		this.code=code;
		this.desc=desc;
	}
	public TriStringObject() {
	
	}

}
