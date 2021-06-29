package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.masterxml.masterworkshop.MasterConfig;
import hisglobal.masterxml.masterworkshop.dao.MasterBuilderDb;
import hisglobal.masterxml.masterworkshop.tools.*;
import hisglobal.persistence.JDBCTransactionContext;

import org.w3c.dom.*;
import java.util.*;

public class MasterAddBuilder
{
	Node masterNode;
	String masterName;
	Document docMasterConfig;
	public static final String CLASS_COMBO = "hisglobal.masterxml.masterworkshop.tools.ComboDtl";
	public static final String CLASS_TEXT = "hisglobal.masterxml.masterworkshop.tools.TextDtl";
	public static final String CLASS_TEXTAREA = "hisglobal.masterxml.masterworkshop.tools.TextAreaDtl";
	public static final String CLASS_RADIOBUTTON = "hisglobal.masterxml.masterworkshop.tools.RadioDtl";
	public static final String CLASS_CHECKBOX = "hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl";

	public MasterAddBuilder()
	{

	}

	public MasterAddBuilder(Document _docMasterConfig, String _masterName)
	{
		this.masterName = _masterName;
		this.docMasterConfig = _docMasterConfig;
		this.masterNode = extractSpecMaster();
		this.removeEmptyTextNode(masterNode);
	}

	//public MasterTO buildMaster(MasterTO _objMasterTo,masterWorkshop.MstHandlerAddFormBn _formBean){   
	public MasterTO buildMaster(MasterTO _objMasterTo)
	{
		MasterDtl objMasterDtl = null;
		//get Add specific MasterDtl
		objMasterDtl = this.getMasterDtl();
		//System.out.println("inside build master");
		//setting values for transfer object        		
		MasterTO objMasterTO = this.getMasterTO(objMasterDtl, _objMasterTo);
		//MasterTO objMasterTO = this.getMasterTO(objMasterDtl,_objMasterTo,_formBean);
		return objMasterTO;
	}

	public MasterDtl getMasterDtl()
	{
		MasterDtl objMasterDtl = null;
		//System.out.println("in get Master dtl");
		MasterAddDtlExtractor objaddDtlExtactor = new MasterAddDtlExtractor(masterNode);
		objMasterDtl = objaddDtlExtactor.getMasterDtl();
		return objMasterDtl;
	}

	//private MasterTO getMasterTO(MasterDtl _objMasterDtl,MasterTO _objLstMasterTo,masterWorkshop.MstHandlerAddFormBn _formBean){
	private MasterTO getMasterTO(MasterDtl _objMasterDtl, MasterTO _objLstMasterTo)
	{
		//System.out.println("inside getMasterTO");
		MasterTO objAddMasterTO = new MasterAddTO();
		boolean hasdependent = false;
		try
		{
			MasterAddDtl objMasterAddDtl = (MasterAddDtl) _objMasterDtl;
			objMasterAddDtl.display();
			if (objMasterAddDtl.getStaticPage() != null)
			{
				((MasterAddTO) objAddMasterTO).setObjMstAddDtl(objMasterAddDtl);
				objAddMasterTO.setTblHeading(_objLstMasterTo.getTblHeading());
				objAddMasterTO.setTitle(_objLstMasterTo.getTitle());
				//System.out.println("_objLstMasterTo.getTitle()" + objAddMasterTO.getTitle());
			}
			else
			{
				//System.out.println("objMasterAddDtl.getAlControls().size()::::" + objMasterAddDtl.getAlControls().size());

				for (int i = 0; i < objMasterAddDtl.getAlControls().size(); i++)
				{
					//System.out.println("inside for ");
					//System.out.println("class name i:::" + i);
					//System.out.println("class name" + objMasterAddDtl.getAlControls().get(i).getClass().getName());
					if (objMasterAddDtl.getAlControls().get(i).getClass().getName().equals(CLASS_TEXT))
					{
						///System.out.println("inside class text");
						ControlDtl objControlDtl = (ControlDtl) objMasterAddDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::"+objControlDtl.getClass().getName());				
						TextDtl objtextDtl = (TextDtl) objMasterAddDtl.getAlControls().get(i);
						TextBox objText = processText(objMasterAddDtl, objtextDtl, _objLstMasterTo);
						objAddMasterTO.getControls().add(objText);
					}

					if (objMasterAddDtl.getAlControls().get(i).getClass().getName().equals(CLASS_TEXTAREA))
					{
						//System.out.println("inside class Area");
						ControlDtl objControlDtl = (ControlDtl) objMasterAddDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						TextAreaDtl objtextareaDtl = (TextAreaDtl) objMasterAddDtl.getAlControls().get(i);
						//TextBox objText=processText(objMasterAddDtl,objtextDtl,_objLstMasterTo,_formBean);				  
						TextArea objTextArea = processTextArea(objMasterAddDtl, objtextareaDtl, _objLstMasterTo);
						objAddMasterTO.getControls().add(objTextArea);
					}
					if (objMasterAddDtl.getAlControls().get(i).getClass().getName().equals(CLASS_COMBO))
					{
						//System.out.println("inside class Combo");
						ControlDtl objControlDtl = (ControlDtl) objMasterAddDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						ComboDtl objComboDtl = (ComboDtl) objMasterAddDtl.getAlControls().get(i);
						//TextBox objText=processText(objMasterAddDtl,objtextDtl,_objLstMasterTo,_formBean);				  
						Combo objCombo = processCombo(objMasterAddDtl, objComboDtl, _objLstMasterTo);
						objAddMasterTO.getControls().add(objCombo);
					}
					if (objMasterAddDtl.getAlControls().get(i).getClass().getName().equals(CLASS_RADIOBUTTON))
					{
						//System.out.println("inside class Radio");
						ControlDtl objControlDtl = (ControlDtl) objMasterAddDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						RadioDtl objRadioDtl = (RadioDtl) objMasterAddDtl.getAlControls().get(i);
						RadioButton objRadio = processRadio(objMasterAddDtl, objRadioDtl, _objLstMasterTo);
						objAddMasterTO.getControls().add(objRadio);
					}
					if (objMasterAddDtl.getAlControls().get(i).getClass().getName().equals(CLASS_CHECKBOX))
					{
						//System.out.println("inside class checkbox");
						ControlDtl objControlDtl = (ControlDtl) objMasterAddDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						CheckBoxDtl objCheckBoxDtl = (CheckBoxDtl) objMasterAddDtl.getAlControls().get(i);
						CheckBox objCheckBox = processCheckBox(objMasterAddDtl, objCheckBoxDtl, _objLstMasterTo);
						objAddMasterTO.getControls().add(objCheckBox);
					}
				}
				((MasterAddTO) objAddMasterTO).setObjMstAddDtl(objMasterAddDtl);
				objAddMasterTO.setTblHeading(_objLstMasterTo.getTblHeading());
				objAddMasterTO.setTitle(_objLstMasterTo.getTitle());
				//System.out.println("_objLstMasterTo.getTitle()" + objAddMasterTO.getTitle());
			}
			return objAddMasterTO;

		}//end of try
		catch (Exception e)
		{
			System.out.println("Exception " + e);
		}//end of catch 
		return objAddMasterTO;
	}//end of getmasterTO

	public CheckBox processCheckBox(MasterAddDtl _objMasterAddDtl, CheckBoxDtl _objCheckBoxDtl, MasterTO _objLstMasterTo)
	{
		boolean readOnly = true;
		CheckBox objChkBox = null;
		//String defaultVal[]=_objCheckBoxDtl.getDefaultValue().split(":");						

		if (_objCheckBoxDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;

		objChkBox = new CheckBox(_objCheckBoxDtl.getLabel(), _objCheckBoxDtl.getDefaultValue(), _objCheckBoxDtl
				.getAlOptionValue(), _objCheckBoxDtl.getAlOptionText(), readOnly, _objCheckBoxDtl.getConstraintMap());
		objChkBox.display();
		return objChkBox;
	}

	public RadioButton processRadio(MasterAddDtl _objMasterAddDtl, RadioDtl _objRadioDtl, MasterTO _objLstMasterTo)
	{
		boolean readOnly = false;
		if (_objRadioDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
		RadioButton objRadio = new RadioButton(_objRadioDtl.getLabel(), _objRadioDtl.getDefaultValue(), _objRadioDtl
				.getAlOptionValue(), _objRadioDtl.getAlOptionText(), readOnly, _objRadioDtl.getConstraintMap());
		objRadio.display();
		return objRadio;
	}

	public Combo processCombo(MasterAddDtl _objMasterAddDtl, ComboDtl _objComboDtl, MasterTO _objLstMasterTo)
	{
		Combo objCombo = null;
		try
		{
			//first check ifdata to be displayed is static or dynamic
			ArrayList alTxtField;
			ArrayList alValField;
			String strComboQuery = "";
			String comboQuery = "";
			ArrayList alDatalist = new ArrayList();
			boolean listDependency = false;
			boolean submit = true;
			int parent;
			boolean readOnly = false;
			String defaultLVal = "";
			String LabelSuffixQueryforCombo = "";
			ArrayList alControls = _objMasterAddDtl.getAlControls();
			//System.out.println("inside process COMBO");
			String hospitalCode = _objLstMasterTo.getHospitalCode();

			if (_objComboDtl.getAlOptionValues().size() > 0)//if static
			{
				//System.out.println("Combo to be build is static");
				alTxtField = new ArrayList(_objComboDtl.getAlOptiontext());
				alValField = new ArrayList(_objComboDtl.getAlOptionValues());
				_objComboDtl.getDefaultLValue();
				//System.out.println("after Combo static");
			}//end of case static
			else
			{//case for dynamic
				if (_objComboDtl.getAlDepCondition().size() == 0)//IF THERE IS NO DEPENDENCY
				{
					alTxtField = new ArrayList();
					alValField = new ArrayList();
					//System.out.println(" inside case dynamic");
					strComboQuery = buildComboQuery(_objComboDtl, hospitalCode);
					//System.out.println("ComboQuery::" + strComboQuery);
					alDatalist = fetchRecord(strComboQuery);
					for (int k = 0; k < alDatalist.size();)
					{
						alTxtField.add(alDatalist.get(k));
						alValField.add(alDatalist.get(k + 1));
						k = k + 2;
					}
				}//END OF CASE IF THERE IS NO DEPENDENCY
				//IF THERE IS DEPENDENCY ON SAME PAGE --DO NOTHING
				//IF TEHRE IS DEPENDENCY ON LIST PAGE
				else
				//deals with case dependecy on list page
				{
					comboQuery = buildComboQuery(_objComboDtl, hospitalCode);
					for (int i = 0; i < _objComboDtl.getAlDepCondition().size(); i++)//for each dependent condition
					{
						String depCondition = (String) _objComboDtl.getAlDepCondition().get(i);
						StringBuffer sb = new StringBuffer(depCondition);
						int start = sb.lastIndexOf("#");
						//String newDependCond=getNewDependCond(depCondition);
						//System.out.println("newDependCond::"+newDependCond);
						int idx = depCondition.lastIndexOf("#");
						String dep = depCondition.substring(idx + 1, idx + 2); //dep represents the value after #
						//System.out.println("dep::" + dep);
						
						////11-Jun-2010
						//Adding liost page dependency for combo
						if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
						{
							parent = getParent(depCondition);
							String val = getDeftValOfParentCtrlfromList(parent, _objLstMasterTo).trim();
							StringBuffer sbe = sb.replace(start, start + 3, "'"+val+"'");
							strComboQuery = strComboQuery + " " + sbe.toString();
							comboQuery=comboQuery+strComboQuery;
							listDependency = true; //to prevent strcomboquery execution because if dependency is not in list				 				 			  			  			  	   		   
						}
						else
						{
							parent = getParent(depCondition);
							//Control objCtrl = (Control) alControls.get(parent);
							ControlDtl objCtrl = (ControlDtl) alControls.get(parent);
							if(objCtrl.getDefaultValue()!=null)
							{
								String val = objCtrl.getDefaultValue().trim();
								//System.out.println("val" + val);
								StringBuffer sbe = sb.replace(start, start + 2, val);
								strComboQuery = strComboQuery + " " + sbe.toString();
								comboQuery=comboQuery+strComboQuery;
							}
						}
					}//for(int i=0;i<_objComboDtl.getAlDepCondition().size();i++)
					/*if (!strComboQuery.equals(""))
					{*/
						alDatalist = fetchRecord(comboQuery);
					/*}
					if (listDependency)
					{
						alDatalist = fetchRecord(comboQuery);
					}*/
					//System.out.println("listDependency" + listDependency);

					alTxtField = new ArrayList();
					alValField = new ArrayList();
					//System.out.println("before");
					for (int k = 0; k < alDatalist.size();)
					{
						alTxtField.add(alDatalist.get(k));
						alValField.add(alDatalist.get(k + 1));
						k = k + 2;
					}
					//System.out.println("after");

				}//deals with case dependecy on list page					

			}//end of else ie data for combo is dynamic 
			//code for label-suffix 

			if (_objComboDtl.getLabelSuffix() != null)
			{
				if (_objComboDtl.getLabelSuffix().getDefaultLValue() != null)//case of static
				{
					defaultLVal = _objComboDtl.getLabelSuffix().getDefaultLValue();
				}
				else if (_objComboDtl.getLabelSuffix().getAlLTable().size() > 0) //ifdata is dynamic and query detail
				{
					//build textQuery will be a overloaded method 			
					LabelSuffixQueryforCombo = buildTextQuery(_objComboDtl.getLabelSuffix());//form generAL QUERY W/O DEP CONDITION
					for (int i = 0; i < _objComboDtl.getLabelSuffix().getAldepLcondition().size(); i++)//for each dependent condition
					{

						String depCondition = (String) _objComboDtl.getLabelSuffix().getAldepLcondition().get(i);
						LabelSuffixQueryforCombo = LabelSuffixQueryforCombo
								+ getTxtQueryBasedOnDependency(LabelSuffixQueryforCombo, depCondition, _objLstMasterTo,
										alControls);
						/*String newDependCond=getNewDependCond(depCondition);  
						int idx=depCondition.lastIndexOf("#");
						 //dep represents value after #	 			  			 			 		 
						 String dep=depCondition.substring(idx+1,idx+2);
						 if(dep.equalsIgnoreCase("L"))//if()dependency is on list page 
						 {		
						   	parent =getParent(depCondition);
							String val=getDeftValOfParentCtrlfromList(parent,_objLstMasterTo).trim();			  
							LabelSuffixQuery=LabelSuffixQuery+" "+newDependCond+val;
							System.out.println("LabelSuffixQuery:: "+LabelSuffixQuery);			 			  			  			  	   		   
						 }			 
						 else //control is dependent on same page
						  {
							parent=getParent(depCondition);
							String val=getDeftValOfParentCtrlfromSame(parent,alControls).trim();	
							System.out.println("val:: label Suffix"+val);			
							LabelSuffixQuery=LabelSuffixQuery+" "+newDependCond+val;	
							System.out.println("textQuery:: label suffix"+LabelSuffixQuery);																													     
						  }*/

					}//end of for
				}//end of case ifelse query detail for label suffix

				//the following code extracts the dependent condition in case of value-detail for label suffix
				else if (_objComboDtl.getLabelSuffix().getAldepLconditionVal().size() > 0)
				{
					for (int j = 0; j < _objComboDtl.getLabelSuffix().getAldepLconditionVal().size(); j++)
					{
						//this code can be put in a method
						String depCondition = (String) _objComboDtl.getLabelSuffix().getAldepLconditionVal().get(j);
						int idx = depCondition.lastIndexOf("#");
						String dep = depCondition.substring(idx + 1);

						if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
						{
							parent = getParent(depCondition);
							Control objControl = (Control) _objLstMasterTo.getControls().get(parent);
							String val = objControl.getDefaultValue();//this val   
							defaultLVal = defaultLVal + val;
						}
						else
						{
							parent = getParent(depCondition);
							ControlDtl objCtrlDtl = (ControlDtl) alControls.get(parent);
							String val = objCtrlDtl.getDefaultValue();
							defaultLVal = defaultLVal + val;
						}

					}
				}//end of ifelse for case value detail for label suffix   							
			}//end of ifof  label suffix
			if (!LabelSuffixQueryforCombo.equalsIgnoreCase(""))
			{
				ArrayList alData = fetchRecord(LabelSuffixQueryforCombo);
				defaultLVal = (String) alData.get(0);
			}
			if (_objComboDtl.getSubmitRequired().equalsIgnoreCase("0")) submit = false;

			objCombo = new Combo(_objComboDtl.getLabel(), _objComboDtl.getDefaultValue(), alValField, alTxtField, readOnly,
					_objComboDtl.getConstraintMap());
			objCombo.setLabelSuffix(defaultLVal);
			//System.out.println("before sumdfh");
			objCombo.setSubmitRequired(submit);
			objCombo.setSelectionMandatory(_objComboDtl.getSelectionMandatory());
			//System.out.println("_objComboDtl.getDefaultValue()::" + _objComboDtl.getDefaultValue());
			objCombo.setDefaultValue(_objComboDtl.getDefaultValue());
			objCombo.display();
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN PROCESS COMBO of add builder" + e);
			e.printStackTrace();
		}
		return objCombo;

	}//end of method processCombo

	public String buildComboQuery(ComboDtl _objComboDtl, String hospitalCode)
	{
		int len = _objComboDtl.getAlTable().size();
		String strTable = "";
		for (int j = 0; j < len; j++)
		{
			strTable = strTable + _objComboDtl.getAlTable().get(j);
			strTable = strTable + " " + _objComboDtl.getAlTableAlias().get(j);
			if (j != (len - 1))
			{
				strTable = strTable + ",";
			}
		}
		String comboQuery = "select " + _objComboDtl.getTextField() + "," + _objComboDtl.getValueField() + " from "
				+ strTable + " where ";

		/////Adding where clause for hospitalCode///////////
		String whereClauseHospitalCode = setHospitalCode(comboQuery, _objComboDtl, hospitalCode);

		comboQuery = whereClauseHospitalCode + _objComboDtl.getGenCondition();

		return comboQuery;
	}

	public TextArea processTextArea(MasterAddDtl _objMasterAddDtl, TextAreaDtl _objtextAreaDtl, MasterTO _objLstMasterTo)
	{
		String defaultVal = "";
		boolean readOnly = true;
		TextArea objTextArea = null;
		String textAreaQuery = "";
		int parent;
		ArrayList alControls = _objMasterAddDtl.getAlControls();
		//System.out.println("inside process textAREA");

		if (_objtextAreaDtl.getDefaultValue() != null)//case of static
		{
			//System.out.println("case staitic val for textarea");
			defaultVal = _objtextAreaDtl.getDefaultValue().trim();
			if (defaultVal == null) defaultVal = "";
			//System.out.println("_objtextAreaDtl.getDefaultValue()::" + _objtextAreaDtl.getDefaultValue());
			//System.out.println("defaultVal for static" + defaultVal);
		}
		else if (_objtextAreaDtl.getAlTable().size() > 0) //ifdata is dynamic and query detail
		{
			textAreaQuery = buildTextAreaQuery(_objtextAreaDtl);//form generAL QUERY W/O DEP CONDITION
			//System.out.println("textQuery:::" + textAreaQuery);
			for (int i = 0; i < _objtextAreaDtl.getAlDepCondition().size(); i++)//for each dependent condition
			{
				String depCondition = (String) _objtextAreaDtl.getAlDepCondition().get(i);
				textAreaQuery = textAreaQuery
						+ getTxtQueryBasedOnDependency(textAreaQuery, depCondition, _objLstMasterTo, alControls);
				/* 
				String newDependCond=getNewDependCond(depCondition);
				System.out.println("newDependCond::"+newDependCond);
				int idx=depCondition.lastIndexOf("#");		   
							 	 			  			 			 		 
				 String dep=depCondition.substring(idx+1); //dep represents the value after #
				 System.out.println("dep::"+dep);	
				 if(dep.equalsIgnoreCase("L"))//if()dependency is on list page 
				 {		
				  parent =getParent(depCondition);
				  String val=getDeftValOfParentCtrlfromList(parent,_objLstMasterTo).trim();			  
				  textAreaQuery=textAreaQuery+" "+newDependCond+val;			 			  			  			  	   		   
				 }			 
				 else //control is dependent on same page
				  {
					parent=getParent(depCondition);
					String val=getDeftValOfParentCtrlfromSame(parent,alControls).trim();	
					System.out.println("val::"+val);			
					textAreaQuery=textAreaQuery+" "+newDependCond+val;	
					System.out.println("textQuery::"+textAreaQuery);												     
				  }*/

			}//end of for
		}//end of case ifelse query detail

		//the following code extracts the dependent condition in case of value-detail
		else if (_objtextAreaDtl.getAlDepConditionVal().size() > 0)
		{
			//System.out.println("inside case value-detail");
			for (int j = 0; j < _objtextAreaDtl.getAlDepConditionVal().size(); j++)
			{
				//System.out.println("inside for of value-detail");
				String depCondition = (String) _objtextAreaDtl.getAlDepConditionVal().get(j);
				defaultVal = defaultVal + getDefValforVD(defaultVal, depCondition, _objLstMasterTo, alControls);
				/*int idx=depCondition.lastIndexOf("#");			    
				 String dep=depCondition.substring(idx+1,idx+2);		
							  			  
				if(dep.equalsIgnoreCase("L"))//if()dependency is on list page 
				  {
					  parent =getParent(depCondition);			  			  			  			  		  			  
					  Control objControl=(Control)_objLstMasterTo.getControls().get(parent);
					  String val=objControl.getDefaultValue();//this val   
					  defaultVal=defaultVal+val;			
				   }
				 else
				 {
				   parent=getParent(depCondition);
				   ControlDtl objCtrlDtl=(ControlDtl)alControls.get(parent);
				   String val=objCtrlDtl.getDefaultValue();
				   defaultVal=defaultVal+val;
				   System.out.println("def value for value detail"+defaultVal);				
				  }
				 */
			}
		}//end of ifelse for case value detail

		if (!textAreaQuery.equalsIgnoreCase(""))
		{
			ArrayList alData = fetchRecord(textAreaQuery);
			defaultVal = (String) alData.get(0);
		}

		if (_objtextAreaDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
		objTextArea = new TextArea(_objtextAreaDtl.getLabel(), defaultVal, _objtextAreaDtl.getConstraintMap(), readOnly);
		objTextArea.display();
		return objTextArea;

	}//end of method processTEXT AREA DTL	

	public String getNewDependCond(String depCondition)
	{
		int idx = depCondition.lastIndexOf("#");
		String newDependCond = depCondition.substring(0, idx - 1);
		return newDependCond;

	}

	public String getDeftValOfParentCtrlfromList(int parent, MasterTO _objLstMasterTo)
	{
		Control objControl = (Control) _objLstMasterTo.getControls().get(parent);
		String val = objControl.getDefaultValue();//this val is used for where clause
		return val;
	}

	public String getDeftValOfParentCtrlfromSame(int parent, ArrayList _alControls)
	{
		ControlDtl objCtrlDtl = (ControlDtl) _alControls.get(parent);
		String val = objCtrlDtl.getDefaultValue().trim();
		return val;
	}

	//QD represents Query detail	 	 
	public String getFinalTextQueryQD(String textQuery, TextDtl _objtextDtl, MasterTO _objLstMasterTo, ArrayList _alControls)
	{

		for (int i = 0; i < _objtextDtl.getAlDepCondition().size(); i++)//for each dependent condition
		{
			String depCondition = (String) _objtextDtl.getAlDepCondition().get(i);
			//System.out.println("depCondition" + depCondition);
			textQuery = textQuery + getTxtQueryBasedOnDependency(textQuery, depCondition, _objLstMasterTo, _alControls);
			//System.out.println("textQuery in for loop " + textQuery);
		}//end of for
		
		for (int i = 0; i < _objtextDtl.getAlAlphanumericDepCondition().size(); i++)//for each dependent condition
		{
			String depCondition = (String) _objtextDtl.getAlAlphanumericDepCondition().get(i);
			//System.out.println("depCondition" + depCondition);
			textQuery = textQuery + getTxtQueryBasedOnAlphanumericDependency(textQuery, depCondition, _objLstMasterTo, _alControls);
			//System.out.println("textQuery in for loop " + textQuery);
		}//end of for
		System.out.println("textQuery:: in getFinalTextQueryQD" + textQuery);
		return textQuery;

	}//end of method getFinalTextQueryQD

	public String getTxtQueryBasedOnDependency(String _txtQuery, String depCondition, MasterTO _objLstMasterTo,
			ArrayList _alControls)
	{
		int parent;
		StringBuffer sb = new StringBuffer(depCondition);
		int startIndex = sb.lastIndexOf("#");
		//String newDependCond=getNewDependCond(depCondition);  
		int idx = depCondition.lastIndexOf("#");
		//dep represents value after #	 			  			 			 		 
		String dep = depCondition.substring(idx + 1, idx + 2);
		if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
		{
			parent = getParent(depCondition);
			String val = getDeftValOfParentCtrlfromList(parent, _objLstMasterTo).trim();
			StringBuffer replacementValue = sb.replace(startIndex, startIndex + 3, val);
			_txtQuery = replacementValue.toString();
			//System.out.println("_txtQuery" + _txtQuery);
		}
		else
		//control is dependent on same page
		{
			parent = getParent(depCondition);
			String val = getDeftValOfParentCtrlfromSame(parent, _alControls).trim();
			//System.out.println("val::" + val);
			StringBuffer replacementValue = sb.replace(startIndex, startIndex + 2, val);
			_txtQuery = replacementValue.toString();
			//System.out.println("_txtQuery::" + _txtQuery);
		}
		System.out.println("_txtQuery" + _txtQuery);
		return _txtQuery;

	}
	
	/////added for alphanumric dependency///
	
	public String getTxtQueryBasedOnAlphanumericDependency(String _txtQuery, String depCondition, MasterTO _objLstMasterTo,
			ArrayList _alControls)
	{
		int parent;
		StringBuffer sb = new StringBuffer(depCondition);
		int startIndex = sb.lastIndexOf("#");
		//String newDependCond=getNewDependCond(depCondition);  
		int idx = depCondition.lastIndexOf("#");
		//dep represents value after #	 			  			 			 		 
		String dep = depCondition.substring(idx + 1, idx + 2);
		if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
		{
			parent = getParent(depCondition);
			String val = getDeftValOfParentCtrlfromList(parent, _objLstMasterTo).trim();
			val="'"+val+"'";
			StringBuffer replacementValue = sb.replace(startIndex, startIndex + 3, val);
			////for alphanumric addin quotes//////
			
			_txtQuery = replacementValue.toString();
			
			//////////////////////////////////////
			//System.out.println("_txtQuery" + _txtQuery);
		}
		else
		//control is dependent on same page
		{
			parent = getParent(depCondition);
			String val = getDeftValOfParentCtrlfromSame(parent, _alControls).trim();
			val="'"+val+"'";
			//System.out.println("val::" + val);
			StringBuffer replacementValue = sb.replace(startIndex, startIndex + 2, val);
			_txtQuery = replacementValue.toString();
			//System.out.println("_txtQuery::" + _txtQuery);
		}
		System.out.println("_txtQuery" + _txtQuery);
		return _txtQuery;

	}

	public String getFinalTextQueryQDLS(String _textLQuery, ControlDtlWithSuffix _objLabelSuffix, MasterTO _objLstMasterTo,
			ArrayList _alControls)
	{

		for (int i = 0; i < _objLabelSuffix.getAldepLcondition().size(); i++)//for each dependent condition
		{
			String depCondition = (String) _objLabelSuffix.getAldepLcondition().get(i);
			_textLQuery = _textLQuery
					+ getTxtQueryBasedOnDependency(_textLQuery, depCondition, _objLstMasterTo, _alControls);

		}//end of for

		return _textLQuery;
	}//end of getFinalTextQueryQDLS

	public String getdefaulValforVD(TextDtl _objtextDtl, MasterTO _objLstMasterTo, ArrayList _alControls)
	{
		String defaultVal = "";
		for (int j = 0; j < _objtextDtl.getAlDepConditionVal().size(); j++)
		{

			//System.out.println("inside for of value-detail");
			String depCondition = (String) _objtextDtl.getAlDepConditionVal().get(j);
			defaultVal = defaultVal + getDefValforVD(defaultVal, depCondition, _objLstMasterTo, _alControls);

		}
		return defaultVal;
	}//end of method getdefaulValforVD	 

	public String getDefValforVD(String defaultVal, String depCondition, MasterTO _objLstMasterTo, ArrayList _alControls)
	{

		int parent;
		int idx = depCondition.lastIndexOf("#");
		String dep = depCondition.substring(idx + 1, idx + 2);

		if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
		{
			parent = getParent(depCondition);
			Control objControl = (Control) _objLstMasterTo.getControls().get(parent);
			String val = objControl.getDefaultValue();//this val   
			defaultVal = defaultVal + val;
		}
		else
		{
			parent = getParent(depCondition);
			ControlDtl objCtrlDtl = (ControlDtl) _alControls.get(parent);
			String val = objCtrlDtl.getDefaultValue();
			defaultVal = defaultVal + val;
			//System.out.println("def value for value detail in textbox" + defaultVal);
		}

		return defaultVal;
	}

	public String getdefaultValforVDLS(ControlDtlWithSuffix _objLabelSuffix, MasterTO _objLstMasterTo, ArrayList _alControls)
	{
		String defaultLVal = "";
		for (int j = 0; j < _objLabelSuffix.getAldepLconditionVal().size(); j++)
		{
			//this code can be put in a method
			String depCondition = (String) _objLabelSuffix.getAldepLconditionVal().get(j);
			defaultLVal = defaultLVal + getDefValforVD(defaultLVal, depCondition, _objLstMasterTo, _alControls);

		}
		return defaultLVal;
	}

	public TextBox processText(MasterAddDtl _objMasterAddDtl, TextDtl _objtextDtl, MasterTO _objLstMasterTo)
	{
		String defaultVal = "";
		String defaultLVal = "";
		boolean readOnly = true;
		TextBox objTextbox = null;
		String textQuery = "";
		String textLQuery = "";

		ArrayList alControls = _objMasterAddDtl.getAlControls();
		//System.out.println("inside process text");

		if (_objtextDtl.getDefaultValue() != null)//case of static
		{
			//System.out.println("case staitic val for text");
			defaultVal = _objtextDtl.getDefaultValue().trim();
			if (defaultVal == null)
			{
				defaultVal = "";
			}
			//System.out.println("objtextDtl.getDefaultValue()::" + _objtextDtl.getDefaultValue());
			//System.out.println("defaultVal for static" + defaultVal);
		}
		else if (_objtextDtl.getAlTable().size() > 0) //ifdata is dynamic and query detail
		{
			textQuery = buildTextQuery(_objtextDtl, _objLstMasterTo.getHospitalCode());//form generAL QUERY W/O DEP CONDITION
			//System.out.println("textQuery:::" + textQuery);
			//this function is used to get the text query appended with the dependent conditions
			textQuery = getFinalTextQueryQD(textQuery, _objtextDtl, _objLstMasterTo, alControls);
		}//end of case ifelse query detail

		//the following code extracts the dependent condition in case of value-detail
		else if (_objtextDtl.getAlDepConditionVal().size() > 0)
		{
			//System.out.println("inside case value-detail");
			//this will get the default value for VALUE DETAIL    
			defaultVal = getdefaulValforVD(_objtextDtl, _objLstMasterTo, alControls);
		}//end of ifelse for case value detail		  

		//code to fetch record from db still to be included
		//for Label Suffix

		if (_objtextDtl.getLabelSuffix() != null)
		{
			if (_objtextDtl.getLabelSuffix().getDefaultLValue() != null)//case of static
			{
				defaultLVal = _objtextDtl.getLabelSuffix().getDefaultLValue();
			}
			else if (_objtextDtl.getLabelSuffix().getAlLTable().size() > 0) //ifdata is dynamic and query detail
			{
				//build textQuery will be a overloaded method 			
				textLQuery = buildTextQuery(_objtextDtl.getLabelSuffix());//form generAL QUERY W/O DEP CONDITION
				//QDLS query detail label suffi
				textLQuery = getFinalTextQueryQDLS(textLQuery, _objtextDtl.getLabelSuffix(), _objLstMasterTo, alControls);

			}//end of case ifelse query detail for label suffix

			//the following code extracts the dependent condition in case of value-detail for label suffix
			else if (_objtextDtl.getLabelSuffix().getAldepLconditionVal().size() > 0)
			{
				defaultLVal = getdefaultValforVDLS(_objtextDtl.getLabelSuffix(), _objLstMasterTo, alControls);

			}//end of ifelse for case value detail for label suffix   							
		}//end of ifof  label suffix
		try
		{
			if (!textQuery.equalsIgnoreCase(""))
			{
				ArrayList alData = fetchRecord(textQuery);
				defaultVal = (String) alData.get(0);
				//System.out.println("defaultVal" + defaultVal);
			}

			if (!textLQuery.equalsIgnoreCase(""))
			{
				ArrayList alData = fetchRecord(textLQuery);
				defaultLVal = (String) alData.get(0);
			}

			if (_objtextDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
			objTextbox = new TextBox(_objtextDtl.getLabel(), defaultVal, _objtextDtl.getConstraintMap(), readOnly);
			objTextbox.setLabelSuffix(defaultLVal);
			objTextbox.display();
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION" + e);
			e.printStackTrace();
		}
		return objTextbox;
	}

	//overloaded method to be used for building text query for label-Suffix 	
	public String buildTextQuery(ControlDtlWithSuffix _objSuffixDtl)
	{
		int len = _objSuffixDtl.getAlLTable().size();
		String strTable = "";
		// System.out.println("objComboDtl.depCondition"+objComboDtl.getDepCondition());							  
		for (int j = 0; j < len; j++)
		{
			strTable = strTable + _objSuffixDtl.getAlLTable().get(j);
			strTable = strTable + " " + _objSuffixDtl.getAlLTableAlias().get(j);
			if (j != (len - 1))
			{
				strTable = strTable + ",";
			}
		}
		String strTextQuery = "select " + _objSuffixDtl.getAlLField().get(0) + "from " + strTable + "where "
				+ _objSuffixDtl.getGenLCondition();

		return strTextQuery;
	}//end of method buildTextQuery

	public String buildTextQuery(TextDtl _objtextDtl, String _hospitalCode)
	{
		int len = _objtextDtl.getAlTable().size();
		String strTable = "";
		// System.out.println("objComboDtl.depCondition"+objComboDtl.getDepCondition());							  
		for (int j = 0; j < len; j++)
		{
			strTable = strTable + _objtextDtl.getAlTable().get(j);
			strTable = strTable + " " + _objtextDtl.getAlTableAlias().get(j);
			if (j != (len - 1))
			{
				strTable = strTable + ",";
			}
		}
		String strTextQuery = "select " + _objtextDtl.getAlField().get(0) + "from " + strTable + " where "
				+ _objtextDtl.getGenCondition();

		if (_objtextDtl.getHospitalCode() != null)
		{
			strTextQuery = strTextQuery + " AND " + _objtextDtl.getHospitalCode() + "=" + _hospitalCode;
		}

		return strTextQuery;
	}//end of method buildTextQuery

	public String buildTextAreaQuery(TextAreaDtl _objtextareaDtl)
	{
		int len = _objtextareaDtl.getAlTable().size();
		String strTable = "";
		// System.out.println("objComboDtl.depCondition"+objComboDtl.getDepCondition());							  
		for (int j = 0; j < len; j++)
		{
			strTable = strTable + _objtextareaDtl.getAlTable().get(j);
			strTable = strTable + " " + _objtextareaDtl.getAlTableAlias().get(j);
			if (j != (len - 1))
			{
				strTable = strTable + ",";
			}
		}
		String strTextQuery = "select " + _objtextareaDtl.getAlField().get(0) + "from " + strTable + " where "
				+ _objtextareaDtl.getGenCondition();

		return strTextQuery;
	}//end of method buildTextAreaQuery

	public int getParent(String _depCondition)
	{
		String parent = "";
		int idx = _depCondition.lastIndexOf("#");
		//System.out.println("idx" + idx);
		String dep = _depCondition.substring(idx + 1, idx + 2);
		//System.out.println("depforr::" + dep);
		if (dep.equalsIgnoreCase("L"))
		{
			parent = _depCondition.substring(idx + 2, idx + 3);
		}
		else parent = dep;
		//System.out.println("parent:::" + parent);
		int parentI = Integer.parseInt(parent.trim());
		//System.out.println("parentI" + parentI);
		return parentI;
	}

	private Node extractSpecMaster()
	{
		Node ndmasterconfig = docMasterConfig.getFirstChild();
		NodeList nl = ndmasterconfig.getChildNodes();
		Node ndmaster = null;

		for (int i = 0; i < nl.getLength(); i++)
		{
			if (nl.item(i).getNodeType() != Node.TEXT_NODE)
			{
				ndmaster = nl.item(i);
				NamedNodeMap mapAttr = ndmaster.getAttributes();
				Node ndattr = mapAttr.getNamedItem("name");
				String attrvalue = ndattr.getNodeValue();
				//System.out.println("attr value" + attrvalue);
				if (attrvalue.equalsIgnoreCase(masterName))
				{
					//System.out.println("Inside if");
					return ndmaster;
				}
			}
		}
		return ndmaster;
	}//end of method extractSpecMaster

	public void removeEmptyTextNode(Node node)
	{
		if (node.hasChildNodes())
		{
			NodeList nl = node.getChildNodes();
			if (nl.getLength() > 1)
			{
				for (int i = 0; i < nl.getLength(); i++)
				{
					Node nd = nl.item(i);
					if (nd.getNodeType() == Node.TEXT_NODE)
					{
						Node prNode = nd.getParentNode();
						Node removend = prNode.removeChild(nd);
						//System.out.println("node removed:::"+removend.getNodeValue());						
					}
				}
			}
			Node child = node.getFirstChild();
			while (child != null)
			{
				removeEmptyTextNode(child);
				child = child.getNextSibling();
			}//end of while				
		}//end of if	   	      		
	}//remove empty text node

	public ArrayList fetchRecord(String _query)
	{
		ArrayList alDatalist = null;
		System.out.println("_query in fetch record::::" + _query);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
			alDatalist = objMstBuildDb.fetchRecord(_query);
			//System.out.println("aldataList " + alDatalist);
			return alDatalist;
		}
		catch (Exception e)
		{

		}
		finally
		{
			tx.close();
			return alDatalist;
		}
	}//end of method

	boolean formNewQuery = false;
	boolean formNewLQuery = false;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//following methods are used when the value of controls needs to be changed for dependencies		

	public String getTxtQueryBasedOnDependencyRe(String _txtQuery, String depCondition, Object[] _controls,
			ArrayList _alControls, String str)
	{

		formNewQuery = false;
		formNewLQuery = false;
		int parent;
		StringBuffer sb = new StringBuffer(depCondition);
		int startIndex = sb.lastIndexOf("#");
		//String newDependCond=getNewDependCond(depCondition);  
		int idx = depCondition.lastIndexOf("#");
		//dep represents value after #	 			  			 			 		 
		String dep = depCondition.substring(idx + 1, idx + 2);
		if (!dep.equalsIgnoreCase("L"))//if()dependency is on list page 
		{
			parent = getParent(depCondition);
			Control objCtrl = (Control) _alControls.get(parent);
			String val = objCtrl.getDefaultValue().trim();
			System.out.println("val::" + val);//get the default value of parent control
			if (!val.equals(_controls[parent]))
			{
				if (str.trim().equalsIgnoreCase("label"))
				{
					formNewLQuery = true;
				}
				else
				{
					formNewQuery = true;
				}
				StringBuffer replacementValue = sb.replace(startIndex, startIndex + 2, val);
				_txtQuery = replacementValue.toString();
				//System.out.println("_txtQuery::" + _txtQuery);
			}
		}
		return _txtQuery;
	}

	boolean chgInvalueDetail = false;
	boolean chgInvalueDetailL = false;

	public String getDefValforVDRe(String defaultVal, String depCondition, ArrayList _alControls, Object[] _controls,
			String str)
	{
		chgInvalueDetail = false;
		chgInvalueDetailL = false;
		int parent;
		int idx = depCondition.lastIndexOf("#");
		String dep = depCondition.substring(idx + 1, idx + 2);
		//check if dependency is on same page										  			  
		if (!dep.equalsIgnoreCase("L"))//if()dependency is NOT on list page 
		{
			parent = getParent(depCondition);
			Control objCtrl = (Control) _alControls.get(parent);
			String val = objCtrl.getDefaultValue();
			//Check for change in parents value with that of form bean   
			if (!val.equals(_controls[parent]))
			{
				if (str.trim().equalsIgnoreCase("label"))
				{
					chgInvalueDetailL = true;
				}
				else
				{
					chgInvalueDetail = true;
				}
				defaultVal = (String) _controls[parent];
				//System.out.println("def value for value detail in textbox" + defaultVal);
			}
		}

		return defaultVal;
	}

	public void reProcessText(Control _objControl, TextDtl _objTextDtl, Object[] _controls, ArrayList _alcontrols,
			String _hospitalCode)
	{
		String textQuery = "";
		String textLQuery = "";
		String defaultVal = "";
		String defaultLVal = "";

		if (_objTextDtl.getAlDepCondition().size() > 0)//same page dependency for query detail
		{
			//System.out.println("size()>0");

			textQuery = buildTextQuery(_objTextDtl, _hospitalCode);
			//for each dependent condition 				
			for (int l = 0; l < _objTextDtl.getAlDepCondition().size(); l++)
			{
				//System.out.println("s for(int l=0;l<objTextDtl.getAlDepCondition().size();l++)");
				//if dependecy of control is on same page
				String depCondition = (String) _objTextDtl.getAlDepCondition().get(l);
				textQuery = textQuery
						+ getTxtQueryBasedOnDependencyRe(textQuery, depCondition, _controls, _alcontrols, "query");
			}
		}//if(objTextDtl.getAlDepCondition().size()>0)
		else if (_objTextDtl.getAlDepConditionVal().size() > 0)//same page dependency for value detail
		{
			//for each dependent condition `
			for (int j = 0; j < _objTextDtl.getAlDepConditionVal().size(); j++)
			{
				//System.out.println("inside for of value-detail");
				String depCondition = (String) _objTextDtl.getAlDepConditionVal().get(j);
				defaultVal = defaultVal + getDefValforVDRe(defaultVal, depCondition, _alcontrols, _controls, "label");
			}

		}//same page dependency for value detail case TEXTBOX

		else if (!((TextBox) _objControl).getLabelSuffix().equals(""))
		{
			if (_objTextDtl.getLabelSuffix().getAlLTable().size() > 0) //if data is dynamic and query detail for label suffix
			{
				textLQuery = buildTextQuery(_objTextDtl.getLabelSuffix());//form generAL QUERY W/O DEP CONDITION
				for (int j = 0; j < _objTextDtl.getLabelSuffix().getAldepLcondition().size(); j++)//for each dependent condition
				{
					String depCondition = (String) _objTextDtl.getLabelSuffix().getAldepLcondition().get(j);
					textLQuery = textLQuery
							+ getTxtQueryBasedOnDependencyRe(textQuery, depCondition, _controls, _alcontrols, "label");
				}//end of for

			}//end of case if data is dynamic and  query detail for label suffix

			if (_objTextDtl.getLabelSuffix().getAldepLconditionVal().size() > 0)//same page dependency for value detail
			{
				//for each dependent condition						 
				for (int j = 0; j < _objTextDtl.getLabelSuffix().getAldepLconditionVal().size(); j++)
				{
					//System.out.println("inside  value-detail for label suffix");
					String depCondition = (String) _objTextDtl.getLabelSuffix().getAldepLconditionVal().get(j);
					defaultLVal = defaultLVal + getDefValforVDRe(defaultLVal, depCondition, _alcontrols, _controls, "label");
				}
				((TextBox) _objControl).setDefaultValue(defaultLVal);

			}//same page dependency for value detail case TEXTBOX	label suffix  
		}//end of label suffix			 

		if (formNewQuery)//dependecy for same page was there
		{
			ArrayList alData = fetchRecord(textQuery);
			defaultVal = (String) alData.get(0);
			((TextBox) _objControl).setDefaultValue(defaultVal);
			formNewQuery = false;
		}
		if (chgInvalueDetail)
		{
			((TextBox) _objControl).setDefaultValue(defaultVal);
		}
		if (chgInvalueDetailL)
		{
			((TextBox) _objControl).setLabelSuffix(defaultLVal);
		}

		if (formNewLQuery)//dependecy for same page was there for label suffix
		{
			ArrayList alData = fetchRecord(textLQuery);
			defaultLVal = (String) alData.get(0);
			((TextBox) _objControl).setLabelSuffix(defaultLVal);
			formNewLQuery = false;
		}

	}//end of reprocesstext

	public void reProcessTextArea(Control _objControl, TextAreaDtl _objTextAreaDtl, Object[] _controls, ArrayList _alcontrols)
	{
		String textQuery = "";
		String defaultVal = "";

		if (_objTextAreaDtl.getAlDepCondition().size() > 0)//same page dependency for query detail
		{
			//System.out.println("size()>0");

			textQuery = buildTextAreaQuery(_objTextAreaDtl);
			//for each dependent condition 				
			for (int l = 0; l < _objTextAreaDtl.getAlDepCondition().size(); l++)
			{
				//System.out.println("s for(int l=0;l<objTextDtl.getAlDepCondition().size();l++)");
				//if dependecy of control is on same page
				String depCondition = (String) _objTextAreaDtl.getAlDepCondition().get(l);
				textQuery = textQuery
						+ getTxtQueryBasedOnDependencyRe(textQuery, depCondition, _controls, _alcontrols, "query");
			}
		}//if(objTextDtl.getAlDepCondition().size()>0)
		else if (_objTextAreaDtl.getAlDepConditionVal().size() > 0)//same page dependency for value detail
		{
			//for each dependent condition `
			for (int j = 0; j < _objTextAreaDtl.getAlDepConditionVal().size(); j++)
			{
				//System.out.println("inside for of value-detail");
				String depCondition = (String) _objTextAreaDtl.getAlDepConditionVal().get(j);
				defaultVal = defaultVal + getDefValforVDRe(defaultVal, depCondition, _alcontrols, _controls, "query");
			}

		}//same page dependency for value detail case TEXTBOX				 

		if (formNewQuery)//dependecy for same page was there
		{
			ArrayList alData = fetchRecord(textQuery);
			defaultVal = (String) alData.get(0);
			((TextArea) _objControl).setDefaultValue(defaultVal);
			formNewQuery = false;
		}
		if (chgInvalueDetail)
		{
			((TextArea) _objControl).setDefaultValue(defaultVal);
		}

	}//end of reprocesstextAREA

	public void reProcessCombo(Control objControl, ComboDtl objComboDtl, Object[] controls, ArrayList alcontrols,
			String _hospitalCode)
	{
		int parent;
		String defaultLVal = "";
		String defaultVal = "";
		String textQuery = "";
		String textLQuery = "";

		//System.out.println("inside case combo");
		for (int n = 0; n < alcontrols.size(); n++)
		{
			Control obj = (Control) alcontrols.get(n);
			//System.out.println("value of control::" + n + " : " + obj.getDefaultValue());
		}
		String strComboQuery = "";
		boolean changeInParent = false;
		String ComboLQuery = "";

		if (objComboDtl.getAlDepCondition().size() > 0)
		{
			//System.out.println("inside combos aldepcondition");
			strComboQuery = buildComboQuery(objComboDtl, _hospitalCode);//form combo query w/o any gen condition
			//System.out.println("strComboQuery" + strComboQuery);
			for (int k = 0; k < objComboDtl.getAlDepCondition().size(); k++)
			{
				String depCondition = (String) objComboDtl.getAlDepCondition().get(k);
				//String newDependCond=getNewDependCond(depCondition);						  
				//System.out.println("newDependCond::"+newDependCond);
				int idx = depCondition.lastIndexOf("#");
				String dep = depCondition.substring(idx + 1); //dep represents the value after #
				//System.out.println("dep::" + dep);

				if (!dep.equalsIgnoreCase("L"))//if()dependency is on list page 
				{
					//System.out.println("inside if case combo dependency on same page");
					parent = getParent(depCondition);
					//compare present parents value(FB) with the previous value(TO)
					Control objCtrl = (Control) alcontrols.get(parent);
					String val = objCtrl.getDefaultValue().trim();
					//System.out.println("val:::in combo" + val);
					//System.out.println("controls[parent]" + controls[parent]);
					if (!val.equals(controls[parent]))
					{
						//System.out.println("" + controls[parent]);
						changeInParent = true;
						//strComboQuery=strComboQuery+" "+newDependCond+controls[parent];                             				
						StringBuffer sb = new StringBuffer(depCondition);
						int start = sb.lastIndexOf("#");
						StringBuffer sbe = sb.replace(start, start + 2, (String) controls[parent]);
						strComboQuery = strComboQuery + " " + sbe.toString();
						//System.out.println("parent: "+parent+" for combo: "+i+" has changed");				   			
						//System.out.println("ComboQuery::" + strComboQuery);
						//System.out.println(" has dependence change Combo:"+i);
						//controls[i]	="-1";						 						 								 				 			  			  			  	   		   
					}
				}
			}//for(int i=0;i<_objComboDtl.getAlDepCondition().size();i++)						

		}// if(objComboDtl.getAlDepCondition().size()>0)	

		else if (!((Combo) objControl).getLabelSuffix().equals(""))
		{
			if (objComboDtl.getLabelSuffix().getAlLTable().size() > 0) //if data is dynamic and query detail for label suffix
			{
				ComboLQuery = buildTextQuery(objComboDtl.getLabelSuffix());//form generAL QUERY W/O DEP CONDITION
				for (int j = 0; j < objComboDtl.getLabelSuffix().getAldepLcondition().size(); j++)//for each dependent condition
				{
					String depCondition = (String) objComboDtl.getLabelSuffix().getAldepLcondition().get(j);
					defaultLVal = defaultLVal
							+ getTxtQueryBasedOnDependencyRe(ComboLQuery, depCondition, controls, alcontrols, "label");
				}

			}//end of for		  

			if (objComboDtl.getLabelSuffix().getAldepLconditionVal().size() > 0)//same page dependency for value detail
			{
				//for each dependent condition						 
				for (int j = 0; j < objComboDtl.getLabelSuffix().getAldepLconditionVal().size(); j++)
				{
					//System.out.println("inside  value-detail for label suffix");
					String depCondition = (String) objComboDtl.getLabelSuffix().getAldepLconditionVal().get(j);
					defaultLVal = defaultLVal + getDefValforVDRe(defaultLVal, depCondition, alcontrols, controls, "label");
				}
			}//same page dependency for value detail case TEXTBOX	label suffix  
		}//end of label suffix						

		if (changeInParent)
		{
			ArrayList alDatalist = fetchRecord(strComboQuery);
			ArrayList alTxtField = new ArrayList();
			ArrayList alValField = new ArrayList();
			for (int k = 0; k < alDatalist.size();)
			{
				alTxtField.add(alDatalist.get(k));
				alValField.add(alDatalist.get(k + 1));
				k = k + 2;
			}
			((Combo) objControl).setText(alTxtField);
			((Combo) objControl).setValue(alValField);
			//((Combo)objControl).setDefaultValue((String)controls[i]);
		}
		if (formNewLQuery)
		{
			ArrayList alData = fetchRecord(textQuery);
			defaultLVal = (String) alData.get(0);
			((Combo) objControl).setLabelSuffix(defaultLVal);
		}
		if (chgInvalueDetailL)
		{
			((Combo) objControl).setLabelSuffix(defaultLVal);
		}

	}//if(objControl.getClass()==MasterConfig.CLASS_COMBO)		

	public MasterTO reBuildAdd(MasterTO _objMstAddTo, hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		try
		{
			//System.out.println("inside rebuilding add");
			int parent;
			String hospitalCode = _objMstAddTo.getHospitalCode();

			Object[] controls = _formBeanAdd.getControls();
			//System.out.println("inside rebuild add");

			for (int i = 0; i < controls.length; i++)
			{
				//System.out.println("controls " + i + ":" + controls[i]);
			}

			ArrayList alcontrols = _objMstAddTo.getControls();
			for (int m = 0; m < alcontrols.size(); m++)
			{
				Control obj = (Control) alcontrols.get(m);
				//System.out.println("value of control::" + m + " : " + obj.getDefaultValue());
			}
			//objAddDtl is used to get dependent condition of controls
			MasterAddDtl objAddDtl = ((MasterAddTO) _objMstAddTo).getObjMstAddDtl();
			ArrayList alcontrolsDtl = objAddDtl.getAlControls();

			//for each control in TO
			for (int i = 0; i < alcontrols.size(); i++)
			{
				//System.out.println("inside for");
				//get the present conntrol to work on from TO 
				Control objControl = (Control) alcontrols.get(i);

				if (objControl.getClass() == MasterConfig.CLASS_TEXT)
				{
					TextDtl objTextDtl = (TextDtl) alcontrolsDtl.get(i);
					//write method reprocess text //objCOntrol represents current control from TO ,CONTROLS IS array OF TYPE OBJECT
					reProcessText(objControl, objTextDtl, controls, alcontrols, _objMstAddTo.getHospitalCode());
				}// if(objControl.getClass()==MasterConfig.CLASS_TEXT)

				if (objControl.getClass() == MasterConfig.CLASS_TEXTAREA)
				{
					TextAreaDtl objTextAreaDtl = (TextAreaDtl) alcontrolsDtl.get(i);
					//write method reprocess text //objCOntrol represents current control from TO ,CONTROLS IS array OF TYPE OBJECT
					reProcessTextArea(objControl, objTextAreaDtl, controls, alcontrols);
				}// if(objControl.getClass()==MasterConfig.CLASS_TEXT)

				if (objControl.getClass() == MasterConfig.CLASS_COMBO)
				{
					//System.out.println("combo encountered");
					ComboDtl objComboDtl = (ComboDtl) alcontrolsDtl.get(i);
					reProcessCombo(objControl, objComboDtl, controls, alcontrols, hospitalCode);
				}
			}//end of for

		}
		catch (Exception e)
		{
			System.out.println("exception:::" + e);
			e.printStackTrace();
		}
		return _objMstAddTo;
	}//end of method

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////following methods contains code for formulation of insert query  

	private String getColumnString(MasterAddDtl _objMstAddDtl)
	{
		String colstr = "";
		for (int j = 0; j < _objMstAddDtl.getAlpkFields().size(); j++)
		{
			colstr = colstr + _objMstAddDtl.getAlpkFields().get(j) + ",";
		}

		ArrayList alFields = _objMstAddDtl.getAlFields();
		int len = alFields.size();
		for (int i = 0; i < alFields.size(); i++)
		{
			if (i == len - 1)
			{
				colstr = colstr + alFields.get(i);
			}
			else
			{
				colstr = colstr + alFields.get(i) + ",";
			}
		}
		return colstr;
	}//private String getColumnString(MasterAddDtl _objMstAddDtl)

	private String getValueString(MasterAddDtl _objMstAddDtl, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		String valString = "";
		boolean containsHash = false;
		boolean multiplehash = false;
		int len = _objMstAddDtl.getAlValueMap().size();
		Object[] ctrlArray = _formBeanAdd.getControls();
		for (int j = 0; j < _objMstAddDtl.getAlpkValue().size(); j++)
		{
			StringBuffer sb = new StringBuffer();
			String[] arrpkValueAtRunTime = ((String) _objMstAddDtl.getAlpkValue().get(j)).split(":");
			int idx = ((String) _objMstAddDtl.getAlpkValue().get(j)).indexOf("#");
			if (idx != -1) containsHash = true;
			if (arrpkValueAtRunTime.length > 1)
			{
				multiplehash = true;
				for (int l = 0; l < arrpkValueAtRunTime.length; l++)
				{
					StringBuffer arrsb = new StringBuffer(arrpkValueAtRunTime[l]);
					int i = arrpkValueAtRunTime[l].indexOf("#");
					int controlindx = Integer.parseInt(arrpkValueAtRunTime[l].substring(i + 1, i + 2));
					String ctrlValue = (String) ctrlArray[controlindx];
					arrsb.replace(i, i + 2, ctrlValue);
					sb.append(arrsb);
				}
			}
			int i = ((String) _objMstAddDtl.getAlpkValue().get(j)).indexOf("#");
			if (!multiplehash && i != -1)
			{
				StringBuffer arrsb = new StringBuffer(arrpkValueAtRunTime[0]);
				int controlindx = Integer.parseInt(arrpkValueAtRunTime[0].substring(i + 1, i + 2));
				String ctrlValue = (String) ctrlArray[controlindx];
				arrsb.replace(i, i + 2, ctrlValue);
				sb.append(arrsb);
			}

			if (containsHash == true)
			{
				valString = valString + sb.toString() + ",";
				multiplehash = false;
			}
			else
			{
				valString = valString + "(" + (String) _objMstAddDtl.getAlpkValue().get(j);
				multiplehash = false;
			}
			if (_objMstAddDtl.getHospitalCode() != null)
			{
				valString = valString + " WHERE " + _objMstAddDtl.getHospitalCode() + "=" + _objLstMstTo.getHospitalCode();
			}

			valString = valString + "),";

		}
		//System.out.println("final valString::::::::::" + valString);

		for (int i = 0; i < _objMstAddDtl.getAlValueMap().size(); i++)
		{
			//System.out.println("i::" + i);
			String strControl = (String) _objMstAddDtl.getAlValueMap().get(i);
			//System.out.println("strControl" + strControl);
			String ctrl = strControl.substring(1);
			//System.out.println("dgasdj");
			if (ctrl.equalsIgnoreCase("entryDate"))
			{
				//System.out.println("inside entrydate");
				if (i == len - 1)
				{
					valString = valString + "sysdate";
				}
				else
				{
					valString = valString + "sysdate" + ",";

				}
			}
			else if (ctrl.equalsIgnoreCase("seatid")) ////seating seat id into query
			{
				//System.out.println("inside seatid");
				if (i == len - 1)
				{
					valString = valString + _objLstMstTo.getSeatId();
				}
				else
				{
					valString = valString + _objLstMstTo.getSeatId() + ",";
				}
			}

			else if (ctrl.equalsIgnoreCase("hospitalCode")) ////seating hospital code into query
			{
				//System.out.println("inside hospitalCode");
				if (i == len - 1)
				{
					valString = valString + _objLstMstTo.getHospitalCode();
				}
				else
				{
					valString = valString + _objLstMstTo.getHospitalCode() + ",";
				}
			}

			else if (ctrl.equalsIgnoreCase("defaultZero")) ////seating default value 0
			{
				//System.out.println("inside defaultZero");
				if (i == len - 1)
				{
					valString = valString + " 0 ";
				}
				else
				{
					valString = valString + " 0 " + ",";
				}
			}

			else if (ctrl.equalsIgnoreCase("defaultOne")) ////seating default value 1
			{
				//System.out.println("inside defaultOne");
				if (i == len - 1)
				{
					valString = valString + " 1 ";
				}
				else
				{
					valString = valString + " 1 " + ",";
				}
			}
			
			else if (ctrl.equalsIgnoreCase("defaultTwo")) ////seating default value 2
			{
				//System.out.println("inside defaultOne");
				if (i == len - 1)
				{
					valString = valString + " 2 ";
				}
				else
				{
					valString = valString + " 2 " + ",";
				}
			}

			else if (strControl.substring(1, 2).equalsIgnoreCase("L"))//check if insertion value is dependent on list page
			{
				//get the control on which it dependent depcontrol represents this control
				//System.out.println("ctrl.substring(2,3)" + strControl.substring(2, 3));

				int depControl = Integer.parseInt(strControl.substring(2, 3));
				//get the default value of this control
				String val = getDeftValOfParentCtrlfromList(depControl, _objLstMstTo).trim();
				if (i == len - 1)
				{
					valString = valString + "'" + val + "'";
				}
				else
				{
					valString = valString + "'" + val + "',";
				}
			}
			else if (strControl.substring(1, 2).equalsIgnoreCase("I"))//check if value is to be inserted in initcap
			{
				//get the control on which it dependent depcontrol represents this control
				//System.out.println("ctrl.substring(2,3)" + strControl.substring(2, 3));

				int InitCtrl = Integer.parseInt(strControl.substring(2, 3));
				//get the default value of this control

				if (i == len - 1)
				{
					valString = valString + "initcap('" + (String) ctrlArray[InitCtrl] + "')";
				}
				else
				{
					valString = valString + "initcap('" + (String) ctrlArray[InitCtrl] + "'),";
				}
			}
			else if (strControl.substring(1, 2).equalsIgnoreCase("U"))//check if value is to be inserted in upper case
			{
				//get the control on which it dependent depcontrol represents this control
				//System.out.println("ctrl.substring(2,3)" + strControl.substring(2, 3));

				int upperCtrl = Integer.parseInt(strControl.substring(2, 3));
				//get the default value of this control

				if (i == len - 1)
				{
					valString = valString + "upper('" + (String) ctrlArray[upperCtrl] + "')";
				}
				else
				{
					valString = valString + "upper('" + (String) ctrlArray[upperCtrl] + "'),";
				}
			}
			else
			{

				//System.out.println("ctrl::" + ctrl);

				int ctrll = Integer.parseInt(ctrl);

				if (i == len - 1)
				{
					valString = valString + "'" + (String) ctrlArray[ctrll] + "'";
				}
				else
				{
					valString = valString + "'" + (String) ctrlArray[ctrll] + "',";
				}

			}
		}//end of for
		return valString;
	}
	
	
	

	private String formInsertQuery(MasterTO _objMstAddTo, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		MasterAddDtl objMstAddDtl = ((MasterAddTO) _objMstAddTo).getObjMstAddDtl();
		String colummns = getColumnString(objMstAddDtl);
		String query = "INSERT INTO " + objMstAddDtl.getTable() + " (" + getColumnString(objMstAddDtl) + ") values("
				+ getValueString(objMstAddDtl, _objLstMstTo, _formBeanAdd) + ")";
		//System.out.println("insert query:::" + query);
		return query;
	}
	
	private String formRedundentQuery(MasterTO _objMstAddTo, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		MasterAddDtl objMstAddDtl = ((MasterAddTO) _objMstAddTo).getObjMstAddDtl();
		//String colummns = getColumnString(objMstAddDtl);
		String query ="";
		query = objMstAddDtl.getRedundentQuery();
		if(query==null || query.equals(""))
		{
			query="";
		}
		else
		{
		String controlIndex=query.substring(query.indexOf("controlindex")+12,query.length());
		//System.out.println("controlindex"+controlIndex);
		int index=Integer.parseInt(controlIndex.trim());
		Object[] ctrlArray = _formBeanAdd.getControls();
		query=query.substring(0, query.indexOf("controlindex"));
		query=query+" trim(upper(' "+ctrlArray[index]+" '))";
		
		///check if query contians hospital code check else add by default
		int hospitalCodeIndex=query.toUpperCase().indexOf("GNUM_HOSPITAL_CODE");
		
		if(hospitalCodeIndex==-1)
		query=query+" and gnum_hospital_code ="+_objLstMstTo.getHospitalCode();
		
		/////only for state master/////////
		int intParent=0;
		String strParent="";
		String val="";
		if(query.indexOf("valueMap#L")>=0)
			
		{
			strParent= query.substring(query.indexOf("valueMap#L")+10, query.indexOf("valueMap#L")+11);
			intParent=Integer.parseInt(strParent);
			Control objControl = (Control) _objLstMstTo.getControls().get(intParent);
			val = objControl.getDefaultValue();
			query=query.replaceAll("valueMap#L0", val);
		}
		
		//////////Only for state master///////////
		
		
		}
		System.out.println("redundent query:::" + query);
		return query;
	}

	String save(MasterTO _objMstAddTo, MasterTO _objLstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		//System.out.println("IN SAVE OF ADD BUILDER");
		boolean recordSaved = false;
		String statusMessage="FALSE";
		int isRedundent=0;
		try
		{
			String insertQuery = formInsertQuery(_objMstAddTo, _objLstTo, _formBeanAdd);
			String redundentQuery=formRedundentQuery(_objMstAddTo, _objLstTo, _formBeanAdd);
			//System.out.println("insertQuery" + insertQuery);
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();
				MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
				if(!redundentQuery.equals(""))
				{
				isRedundent=objMstBuildDb.redundentCheck(redundentQuery);
				}
				if(isRedundent==0)
				{
				recordSaved = objMstBuildDb.updateRecord(insertQuery);
				}
				//System.out.println("after insert");
			}
			catch (HisDataAccessException e)
			{
				throw new HisDataAccessException();
			}

			finally
			{
				if(isRedundent>=1)
				{
					statusMessage="DUPLICATE";
				}
				else
				{
					if(recordSaved==true)
					{
						statusMessage="TRUE";
					}
					else
					{
						statusMessage="FALSE";
					}
				}
				tx.close();
			}

		}
		catch (Exception e)
		{
			System.out.println("exception ::" + e);
			e.printStackTrace();
		}
		System.out.println("recordSaved::" + recordSaved);
		return statusMessage;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////END of methods that contains code for formulation of insert query

	private String setHospitalCode(String _query, ComboDtl _objComboDtl, String _hospitalCode)
	{

		if (_objComboDtl.getHospitalCode() != null)
		{
			_query = _query + " " + _objComboDtl.getHospitalCode() + "=" + _hospitalCode;
		}
		return _query;
	}

}//end of class

