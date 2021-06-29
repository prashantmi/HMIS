/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TemplateHelper;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

import TemplateHelper.vo.ValueObject;
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

