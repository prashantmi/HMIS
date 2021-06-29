/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package TemplateHelper;

import TemplateHelper.vo.ValueObject;

/***************************Start of program*****************************
 ## Copyright Information			: C-DAC, Noida  
 ## Project Name				: CCD SDK
 ## Name of Developer		 		: Siddharth Srivastava
 ## Module Name					: Health Standards
 ## Process/Database Object Name	        : 
 ## Purpose                                     :
 ## Date of Creation				: 
 ## Modification Log				:				
 ##		Modify Date			: 
 ##		Reason	(CR/PRS)		: 
 ##		Modify By			: 
*/

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */


/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

import TemplateHelper.vo.ValueObject;
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

    /**
     * @return the loincCode
     */
    public String getLoincCode() {
        return loincCode;
    }

    /**
     * @param loincCode the loincCode to set
     */
    public void setLoincCode(String loincCode) {
        this.loincCode = loincCode;
    }

}

