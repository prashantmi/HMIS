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

import java.io.Serializable;

public class PentaStringObject implements Serializable{
	private String primaryID;
	private String firstLabel;
	private String secondLabel;
	private String thirdLabel;
	private String fourthLabel;

	
	public PentaStringObject(String primaryID,String firstLabel,	String secondLabel,	String thirdLabel,String fourthLabel)
	{
		this.primaryID=primaryID;
		this.firstLabel=firstLabel;
		this.secondLabel=secondLabel;
		this.thirdLabel=thirdLabel;
		this.fourthLabel=fourthLabel;
	}


	public String getFirstLabel() {
		return firstLabel;
	}


	public void setFirstLabel(String firstLabel) {
		this.firstLabel = firstLabel;
	}


	public String getFourthLabel() {
		return fourthLabel;
	}


	public void setFourthLabel(String fourthLabel) {
		this.fourthLabel = fourthLabel;
	}


	public String getPrimaryID() {
		return primaryID;
	}


	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}


	public String getSecondLabel() {
		return secondLabel;
	}


	public void setSecondLabel(String secondLabel) {
		this.secondLabel = secondLabel;
	}


	public String getThirdLabel() {
		return thirdLabel;
	}


	public void setThirdLabel(String thirdLabel) {
		this.thirdLabel = thirdLabel;
	}
}

