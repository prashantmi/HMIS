package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hisglobal.utility.Entry;
import hisglobal.vo.OpdTemplateParameterVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TemplateParameterMasterFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;

	private String templateId;
	private String tempSerialNo;
	private String crNo;
	private String templateName;
	private String templateType;
	private int noOfRows;
	private String parameterValuesList;
	private String[] actualParaVal;
	private String htmlTemplate;
	private OpdTemplateParameterVO[][] tempParaMatrix;
	private Map actualParameterValues;
	private String ageRangeId;//added by swati on date:23-08-2019
	private String genderCode;//added by swati on date:23-08-2019
	private String ageRangeName;//added by swati on date:23-08-2019
	private String Gender;//added by swati on date:23-08-2019
	private String strGenderName;//added by swati on date:23-08-2019

	private String effectiveFrom;
	private String effectiveTo;
	private String entryDate;
	
	private String transactionMode;	
	private String choice;
	private String modeTempModify; // 0-No  1-Yes
	
	public String getModeTempModify() {
		return modeTempModify;
	}

	public void setModeTempModify(String modeTempModify) {
		this.modeTempModify = modeTempModify;
	}

	public TemplateParameterMasterFB()
	{
		this.hmode="";
		this.templateId="";
		this.tempSerialNo="";
		this.templateName="";
		this.templateType="0";
		this.crNo="";
		this.noOfRows=0;
		this.parameterValuesList="";
		this.actualParaVal=new String[0];
		this.htmlTemplate="";
		this.tempParaMatrix=new OpdTemplateParameterVO[0][0];
		this.effectiveFrom="";
		this.effectiveTo="";
		
		this.entryDate="";
		this.transactionMode="";
		this.choice="";
		this.modeTempModify="";
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request) 
	{
		super.reset(mapping,request);
		this.templateId="";
		this.templateName="";
		this.templateType="0";
		this.crNo="";
		this.noOfRows=0;
		this.parameterValuesList="";
		this.actualParaVal=new String[0];
		this.htmlTemplate="";
		this.tempParaMatrix=new OpdTemplateParameterVO[0][0];
		this.effectiveFrom="";
		this.effectiveTo="";

		this.entryDate="";
		this.transactionMode="";
		this.choice="";
		this.modeTempModify="";
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public int getNoOfRows() {
		return noOfRows;
	}
	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public OpdTemplateParameterVO[][] getTempParaMatrix() {
		return tempParaMatrix;
	}
	public void setTempParaMatrix(OpdTemplateParameterVO[][] tempParaMatrix) {
		this.tempParaMatrix = tempParaMatrix;
	}
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	public Map getActualParameterValues() {
		return actualParameterValues;
	}
	public void setActualParameterValues(Map actualParameterValues) {
		this.actualParameterValues = actualParameterValues;
	}

	public void setBlankTemplate()
	{
		String h="\n<div id='tblTemplate'><table width='100%'>";
		
		for(int i=0;i<this.noOfRows;i++)
		{
			h+="\n\t<tr>";
			for(int j=0;j<9;j++)
			{
				h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
				h+="\n\t\t\t<table width='100%' id='"+(i+1)+"&"+(j+1)+"' onclick='CellOnFocus(this)' border='0' cellpadding='0' cellspacing='1' >";
				h+="\n\t\t\t\t<tr>";
				h+="\n\t\t\t\t\t<td id='tdTemplate@"+(i+1)+"&"+(j+1)+"' align='center' >";
				h+="\n\t\t\t\t\t\tClick Here";
				h+="\n\t\t\t\t\t</td>";
				h+="\n\t\t\t\t</tr>";
				h+="\n\t\t\t</table>";
				h+="\n\t\t</td>";
			}
			h+="\n\t</tr>";
		}
		h+="\n</table></div>";
		
		this.htmlTemplate=h;
	}
	
	public void generateTemplate()
	{
		String h="\n<table width='100%' id='tblTemplate#"+this.templateId+"' border='0' cellspacing='1' cellpadding='0'>";
		
		for(int i=0;i<this.noOfRows;i++)
			for(int j=0;j<9;j++)
				if(this.tempParaMatrix[i][j]!=null)
				{
					int columns= Integer.parseInt(this.tempParaMatrix[i][j].getColspan());
					for(int k=1;k<columns;k++)
					{
						OpdTemplateParameterVO obj=new OpdTemplateParameterVO();
						obj.setValueObjId("-1");
						this.tempParaMatrix[i][j+k]=obj;
					}
					j+=columns-1;
				}
			
		if(Integer.parseInt(this.templateType)==3)//Consent Print Button Add
		{
			h+="\n<tr><td id='tdPrintConsent' colspan='9' align='right'> <img style='cursor:pointer' src='../hisglobal/images/Print.gif' alt='Print' title='Print' onclick=\"PrintConset('tblTemplate#"+this.templateId+"');\">&nbsp;&nbsp;";
			h+="\n<img style='cursor:pointer' src='../hisglobal/images/viewpdf.gif' alt='Print' title='Print' onclick=\"convertConsetToPdf('tblTemplate#"+this.templateId+"',event);\"></td></tr>";
		}
		for(int i=0;i<this.noOfRows;i++)
		{
			h+="\n\t<tr>";
			for(int j=0;j<9;j++)
			{
				h+=generateCellCode(i,j);	
			}
			h+="\n\t</tr>";
		}
		h+="\n</table>";
		
		this.htmlTemplate=h;
	}
	
	
	String generateCellCode(int r,int c)
	{
		String h="";
		switch(Integer.parseInt(this.templateType))
		{
			case 1:
				h= generateCellCodeNormal(r,c);
				break;
			case 2:
				h= generateCellCodeMatrix(r,c);
				break;
			case 3:
				h= generateCellCodeConsent(r,c);
				break;
		}
		return h;
	}

	String generateCellCodeNormal(int r,int c)
	{
		String h="";
		if(this.tempParaMatrix[r][c]==null)
		{
			h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'><table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			h+="\n\t\t<tr><td class='tdfont'></td></tr></table></div></td>";

		}
		else if(!this.tempParaMatrix[r][c].getValueObjId().equals("-1"))
		{
			OpdTemplateParameterVO vo=this.tempParaMatrix[r][c];
			TemplateParameterFormatBean cPL=new TemplateParameterFormatBean(Integer.parseInt(this.getTemplateType()),Integer.parseInt(vo.getValueObjId()),vo.getValueObjProp() );
			cPL.setParaValue(vo.getParaValue().split("@")[0]);
			if(vo.getParaValue().split("@").length>1)cPL.setParaOptions(vo.getParaValue().split("@")[1]);

			// **** Setting Actual Value ************
			if(this.actualParameterValues!=null)
			{
				if(this.actualParameterValues.get(tempParaMatrix[r][c].getParaId())!=null)
				{
					String value=(String)this.actualParameterValues.get(tempParaMatrix[r][c].getParaId());
					if(!value.equals(""))
						cPL.setDefaultValue(value);
				}
			}
			// ****************
			
			//	1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox
			String[] arrTwo;
			switch(Integer.parseInt(vo.getValueObjId()))
			{
				case 1:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%' colspan='"+vo.getColspan()+"'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' ><div align='"+cPL.getAlign()+"'> ";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</div></td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 2:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";

					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' maxlength='"+cPL.getMaxlength()+"' value='"+cPL.getDefaultValue()+"' size='10' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' onblur=\"validatePARAMETERRegExp(this,event,'"+cPL.getFormat()+"','"+cPL.getRegularExpression()+"');\"/>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 3:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";

					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<textarea name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' rows='2' cols='10' >"+cPL.getDefaultValue()+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 4:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";

					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<select name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" selected='true' ";
						h+=">"+arrTwo[j].split(":")[1]+"</option>";
					}
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 5:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" checked='true' ";	
						h+=">"+arrTwo[j].split(":")[1]+"</input>";
						if((j+1)<arrTwo.length) h+="<br>";
					}
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 6:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";

					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					if(cPL.getDefaultValue().equals("yes"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes' checked='true'>Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes'>Yes</input>";
					if(cPL.getDefaultValue().equals("no"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' checked='true'>No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' >No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 7:
					h+="\n\t\t<td class='tdfonthead' ><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";

					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					arrTwo=cPL.getParaOptions().split(":");
					h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[0]+"' ";
					if(arrTwo[0].equals(cPL.getDefaultValue()))
						h+=" checked='true' ";	
					h+=">"+arrTwo[1]+"</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
			}
		}
		return h;
	}
	
	String generateCellCodeMatrix(int r,int c)
	{
		String h="";
		if(this.tempParaMatrix[r][c]==null)
		{
			h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'><table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			h+="\n\t\t<tr><td class='tdfont' ></td></tr></table></div></td>";

		}
		else if(!this.tempParaMatrix[r][c].getValueObjId().equals("-1"))
		{
			OpdTemplateParameterVO vo=this.tempParaMatrix[r][c];
			TemplateParameterFormatBean cPL=new TemplateParameterFormatBean(Integer.parseInt(this.getTemplateType()),Integer.parseInt(vo.getValueObjId()),vo.getValueObjProp() );
			if(vo.getParaValue().split("@").length>0)cPL.setParaValue(vo.getParaValue().split("@")[0]);
			if(vo.getParaValue().split("@").length>1)cPL.setParaOptions(vo.getParaValue().split("@")[1]);


			// **** Setting Actual Value ************
			if(this.actualParameterValues!=null)
			{
				if(this.actualParameterValues.get(tempParaMatrix[r][c].getParaId())!=null)
				{
					String value=(String)this.actualParameterValues.get(tempParaMatrix[r][c].getParaId());
					if(!value.equals(""))
						cPL.setDefaultValue(value);
				}
			}
			// ****************

			//	1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox
			String[] arrTwo;
			switch(Integer.parseInt(vo.getValueObjId()))
			{
				case 1:
					h+="\n\t\t<td class='tdfonthead' width='"+(100/9)+"%' colspan='"+vo.getColspan()+"'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfonthead' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'><div  align='"+cPL.getAlign()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</div></td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 2:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' maxlength='"+cPL.getMaxlength()+"' value='"+cPL.getDefaultValue()+"' size='10' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' onblur=\"validatePARAMETERRegExp(this,event,'"+cPL.getFormat()+"','"+cPL.getRegularExpression()+"');\"/>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 3:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<textarea name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' rows='2' cols='10' >"+cPL.getDefaultValue()+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 4:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<select name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" selected='true' ";
						h+=">"+arrTwo[j].split(":")[1]+"</option>";
					}
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 5:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' >";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" checked='true' ";	
						h+=">"+arrTwo[j].split(":")[1]+"</input>";
						if((j+1)<arrTwo.length) h+="<br>";
					}
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 6:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					if(cPL.getDefaultValue().equals("yes"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes' checked='true'>Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes'>Yes</input>";
					if(cPL.getDefaultValue().equals("no"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' checked='true'>No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' >No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
					
				case 7:
					h+="\n\t\t<td class='tdfont' width='"+(100/9)+"%'><div align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td class='tdfont' id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					arrTwo=cPL.getParaOptions().split(":");
					h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[0]+"' ";
					if(arrTwo[0].equals(cPL.getDefaultValue()))
						h+=" checked='true' ";	
					h+=">"+arrTwo[1]+"</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</div></td>";
					break;
			}
		}
		return h;
	}

	String generateCellCodeConsent(int r,int c)
	{
		String h="";
		if(this.tempParaMatrix[r][c]==null)
		{
			h+="\n\t\t<td width='"+(100/9)+"%' align='center'><table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			h+="\n\t\t<tr><td></td></tr></table></td>";

		}
		else if(!this.tempParaMatrix[r][c].getValueObjId().equals("-1"))
		{
			OpdTemplateParameterVO vo=this.tempParaMatrix[r][c];
			TemplateParameterFormatBean cPL=new TemplateParameterFormatBean(Integer.parseInt(this.getTemplateType()),Integer.parseInt(vo.getValueObjId()),vo.getValueObjProp() );
			if(vo.getParaValue().split("@").length>0)cPL.setParaValue(vo.getParaValue().split("@")[0]);
			if(vo.getParaValue().split("@").length>1)cPL.setParaOptions(vo.getParaValue().split("@")[1]);

			// **** Setting Actual Value ************
			if(this.actualParameterValues!=null)
			{
				if(this.actualParameterValues.get(tempParaMatrix[r][c].getParaId())!=null)
				{
					String value=(String)this.actualParameterValues.get(tempParaMatrix[r][c].getParaId());
					if(!value.equals(""))
						cPL.setDefaultValue(value);
				}
			}
			// ****************

			//	1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox
			String[] arrTwo;
			switch(Integer.parseInt(vo.getValueObjId()))
			{
				case 1:
					h+="\n\t\t<td width='"+(100/9)+"%' colspan='"+vo.getColspan()+"' align='center' >";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='"+cPL.getAlign()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 2:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' maxlength='"+cPL.getMaxlength()+"' value='"+cPL.getDefaultValue()+"' size='10' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' onblur=\"validatePARAMETERRegExp(this,event,'"+cPL.getFormat()+"','"+cPL.getRegularExpression()+"');\"/>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 3:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<textarea name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' onkeypress='return ("+cPL.getValidationFunction()+"(this,event) && notSpecChar(this,event))' rows='2' cols='10' >"+cPL.getDefaultValue()+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 4:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					h+="\n\t\t\t\t\t\t<select name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" selected='true' ";
						h+=">"+arrTwo[j].split(":")[1]+"</option>";
					}
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 5:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					arrTwo=cPL.getParaOptions().split("~");
					for(int j=0;j<arrTwo.length;j++)
					{
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[j].split(":")[0]+"' ";
						if(arrTwo[j].split(":")[0].equals(cPL.getDefaultValue()))
							h+=" checked='true' ";	
						h+=">"+arrTwo[j].split(":")[1]+"</input>";
						if((j+1)<arrTwo.length) h+="<br>";
					}
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 6:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"'>";
					if(cPL.getDefaultValue().equals("yes"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes' checked='true'>Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='yes'>Yes</input>";
					if(cPL.getDefaultValue().equals("no"))
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' checked='true'>No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"' value='no' >No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 7:
					h+="\n\t\t<td width='"+(100/9)+"%' align='center'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='left' >";
					arrTwo=cPL.getParaOptions().split(":");
					h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARAMETER@"+vo.getParaId()+"&"+this.templateId+"' id='C:"+vo.getIsCompulsory()+"&R:"+vo.getIsRange()+"' value='"+arrTwo[0]+"' ";
					if(arrTwo[0].equals(cPL.getDefaultValue()))
						h+=" checked='true' ";	
					h+=">"+arrTwo[1]+"</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
			}
		}
		return h;
	}

	public void addSeqGenerateReport(List lst)
	{
		String h="\n<tr><td colspan='4'><table width='100%' id='tblTemplate' cellspacing='1' cellpadding='0'>";

		for(int i=0;i<this.noOfRows;i++)
			for(int j=0;j<9;j++)
				if(this.tempParaMatrix[i][j]!=null)
				{
					int columns= Integer.parseInt(this.tempParaMatrix[i][j].getColspan());
					for(int k=1;k<columns;k++)
					{
						OpdTemplateParameterVO obj=new OpdTemplateParameterVO();
						obj.setValueObjId("-1");
						this.tempParaMatrix[i][j+k]=obj;
					}
					j+=columns-1;
				}
		
		for(int i=0;i<this.noOfRows;i++)
		{
			h+="\n\t<tr>";
			for(int j=0;j<9;j++)
			{
				h+=generateReportCellCode(i,j,lst);	
			}
			h+="\n\t</tr>";
		}
		h+="\n</table></td></tr>";
		
		this.htmlTemplate+=h;
	}

	
	public void generateReport(List lst)
	{
		String h="\n<tr><td colspan='4'><table width='100%' id='tblTemplate' cellspacing='1' cellpadding='0'>";

		for(int i=0;i<this.noOfRows;i++)
			for(int j=0;j<9;j++)
				if(this.tempParaMatrix[i][j]!=null)
				{
					int columns= Integer.parseInt(this.tempParaMatrix[i][j].getColspan());
					for(int k=1;k<columns;k++)
					{
						OpdTemplateParameterVO obj=new OpdTemplateParameterVO();
						obj.setValueObjId("-1");
						this.tempParaMatrix[i][j+k]=obj;
					}
					j+=columns-1;
				}
		
		for(int i=0;i<this.noOfRows;i++)
		{
			h+="\n\t<tr>";
			for(int j=0;j<9;j++)
			{
				h+=generateReportCellCode(i,j,lst);	
			}
			h+="\n\t</tr>";
			
		}
		h+="\n</table></td></tr>";
		
		this.htmlTemplate=h;
	}
	
	String generateReportCellCode(int r,int c,List lst)
	{
		String h="";
		switch(Integer.parseInt(this.templateType))
		{
			case 1:
				h= generateReportCellCodeNormal(r,c,lst);
				break;
			case 2:
				h= generateReportCellCodeMatrix(r,c,lst);
				break;
			case 3:
				h= generateReportCellCodeMatrix(r,c,lst);
				break;
		}
		return h;
	}

	String generateReportCellCodeNormal(int r,int c,List lst)
	{
		String h="";
		if(this.tempParaMatrix[r][c]==null)
		{
			h+="\n\t\t<td align='center' width='"+(100/9)+"%'><table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			h+="\n\t\t<tr><td align='center' ></td></tr></table></td>";

		}
		else if(!this.tempParaMatrix[r][c].getValueObjId().equals("-1"))
		{
			OpdTemplateParameterVO vo=this.tempParaMatrix[r][c];
			TemplateParameterFormatBean cPL=new TemplateParameterFormatBean(Integer.parseInt(this.getTemplateType()),Integer.parseInt(vo.getValueObjId()),vo.getValueObjProp() );
			cPL.setParaValue(vo.getParaValue().split("@")[0]);
			if(vo.getParaValue().split("@").length>1)cPL.setParaOptions(vo.getParaValue().split("@")[1]);
			
			String value="";

			// **** Setting Actual Value ************
			if(this.actualParameterValues!=null)
				if(this.actualParameterValues.get(tempParaMatrix[r][c].getParaId())!=null)
					value=(String)this.actualParameterValues.get(tempParaMatrix[r][c].getParaId());
			// ****************

			for(int i=0;i<lst.size();i++)
			{
				Entry entObj=(Entry)lst.get(i);
				//if(entObj.getValue().split("#")[0].equals(vo.getParaId()))
				if(entObj.getValue().equals(vo.getParaId()))
					value=entObj.getLabel();
 			}
			
			//	1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox
			switch(Integer.parseInt(vo.getValueObjId()))
			{
				case 1:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%' colspan='"+vo.getColspan()+"'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='"+cPL.getAlign()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 2:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 3:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 4:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 5:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 6:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 7:
					h+="\n\t\t<td align='center' >";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' >";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center' >";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
			}
		}
		return h;
	}

	String generateReportCellCodeMatrix(int r,int c,List lst)
	{
		String h="";
		if(this.tempParaMatrix[r][c]==null)
		{
			h+="\n\t\t<td align='center' width='"+(100/9)+"%'><table width='100%' border='0' cellspacing='1' cellpadding='0'>";
			h+="\n\t\t<tr><td align='center' ></td></tr></table></td>";

		}
		else if(!this.tempParaMatrix[r][c].getValueObjId().equals("-1"))
		{
			OpdTemplateParameterVO vo=this.tempParaMatrix[r][c];
			TemplateParameterFormatBean cPL=new TemplateParameterFormatBean(Integer.parseInt(this.getTemplateType()),Integer.parseInt(vo.getValueObjId()),vo.getValueObjProp() );
			if(vo.getParaValue().split("@").length>0)cPL.setParaValue(vo.getParaValue().split("@")[0]);
			if(vo.getParaValue().split("@").length>1)cPL.setParaOptions(vo.getParaValue().split("@")[1]);
			
			String value="";
			// **** Setting Actual Value ************
			if(this.actualParameterValues!=null)
				if(this.actualParameterValues.get(tempParaMatrix[r][c].getParaId())!=null)
					value=(String)this.actualParameterValues.get(tempParaMatrix[r][c].getParaId());
			// ****************
			for(int i=0;i<lst.size();i++)
			{
				Entry entObj=(Entry)lst.get(i);
				//if(entObj.getValue().split("#")[0].equals(vo.getParaId()))
				if(entObj.getValue().equals(vo.getParaId()))
				{
					value=entObj.getLabel();
					break;
				}
 			}
			
			//	1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox
			switch(Integer.parseInt(vo.getValueObjId()))
			{
				case 1:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%' colspan='"+vo.getColspan()+"'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='"+cPL.getAlign()+"'>";
					h+="\n\t\t\t\t\t\t";
					if(cPL.getBold().equals("true"))h+="<b>";
					if(cPL.getItalic().equals("true"))h+="<i>";
					if(cPL.getUnderlined().equals("true"))h+="<u>";
					h+="<font color='"+cPL.getColor()+"'>"+cPL.getParaValue()+"</font>";
					if(cPL.getUnderlined().equals("true"))h+="</u>";
					if(cPL.getItalic().equals("true"))h+="</i>";
					if(cPL.getBold().equals("true"))h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 2:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 3:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 4:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 5:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 6:
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center'>";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
					
				case 7:
					h+="\n\t\t<td align='center' >";
					h+="\n\t\t\t<table width='100%' id='"+vo.getRowId()+"&"+vo.getColumnId()+"' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+vo.getRowId()+"&"+vo.getColumnId()+"' align='center' >";
					h+="\n\t\t\t\t\t\t\t<b>"+value+"</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
			}
		}
		return h;
	}

	
	
	
	
	public void getParaValueReportCode(List lst)
	{
		String h="";
		for(int i=0;i<lst.size();i++)
		{
			Entry entObj=(Entry)lst.get(i);
			h+="\n\t<tr>";

			h+="\n\t\t<td class='tdfonthead' width='25%'><div align='center'><font color='#FF0000'><b>"+entObj.getValue().split("#")[1]+"</b></font></div></td>";
			h+="\n\t\t<td class='tdfont' width='25%'><b>"+entObj.getLabel()+"</b></td>";
			h+="\n\t\t<td class='tdfonthead' width='25%'></td>";
			h+="\n\t\t<td class='tdfont' width='25%'></td>";
			
			h+="\n\t</tr>";
		}
		
		this.htmlTemplate=h;
	}
	
	

	public String getParameterValuesList() {
		return parameterValuesList;
	}


	public void setParameterValuesList(String parameterValuesList) {
		this.parameterValuesList = parameterValuesList;
	}


	public String getCrNo() {
		return crNo;
	}


	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}


	public String[] getActualParaVal() {
		return actualParaVal;
	}


	public void setActualParaVal(String[] actualParaVal) {
		this.actualParaVal = actualParaVal;
	}


	public String getTemplateType() {
		return templateType;
	}


	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getTempSerialNo() {
		return tempSerialNo;
	}

	public void setTempSerialNo(String tempSerialNo) {
		this.tempSerialNo = tempSerialNo;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getAgeRangeId() {
		return ageRangeId;
	}

	public void setAgeRangeId(String ageRangeId) {
		this.ageRangeId = ageRangeId;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getAgeRangeName() {
		return ageRangeName;
	}

	public void setAgeRangeName(String ageRangeName) {
		this.ageRangeName = ageRangeName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getStrGenderName() {
		return strGenderName;
	}

	public void setStrGenderName(String strGenderName) {
		this.strGenderName = strGenderName;
	}
	
	
	
	
}
