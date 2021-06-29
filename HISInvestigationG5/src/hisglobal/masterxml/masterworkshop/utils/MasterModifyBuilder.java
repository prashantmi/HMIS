package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.masterxml.masterworkshop.MasterConfig;
import hisglobal.masterxml.masterworkshop.dao.MasterBuilderDb;
import hisglobal.masterxml.masterworkshop.tools.*;
import hisglobal.persistence.JDBCTransactionContext;

import java.util.*;
import org.w3c.dom.*;

public class MasterModifyBuilder
{
	Node masterNode;
	String masterName;
	Document docMasterConfig;
	public static final String CLASS_COMBO = "hisglobal.masterxml.masterworkshop.tools.ComboDtl";
	public static final String CLASS_TEXT = "hisglobal.masterxml.masterworkshop.tools.TextDtl";
	public static final String CLASS_TEXTAREA = "hisglobal.masterxml.masterworkshop.tools.TextAreaDtl";
	public static final String CLASS_RADIOBUTTON = "hisglobal.masterxml.masterworkshop.tools.RadioDtl";
	public static final String CLASS_CHECKBOX = "hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl";

	public MasterModifyBuilder(Document _docMasterConfig, String _masterName)
	{
		this.masterName = _masterName;
		this.docMasterConfig = _docMasterConfig;
		this.masterNode = extractSpecMaster();
		this.removeEmptyTextNode(masterNode);
	}

	public MasterModifyBuilder()
	{

	}

	//public MasterTO buildMaster(MasterTO _objMasterTo,hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBean){   

	public MasterTO buildMaster(String _chk, String _activeStatus, MasterTO _objMstTo)
	{
		MasterDtl objMasterDtl = null;
		//get Add specific MasterDtl
		objMasterDtl = this.getMasterDtl();
		System.out.println("inside build master sdfdfdf");
		//setting values for transfer object        		
		// MasterTO objMasterTO = this.getMasterTO(objMasterDtl,_formBeanMod);		    
		MasterTO objMasterTO = this.getMasterTO(objMasterDtl, _chk, _activeStatus, _objMstTo);
		return objMasterTO;
	}

	public MasterDtl getMasterDtl()
	{
		MasterDtl objMasterDtl = null;
		System.out.println("in get Master dtl");
		MasterModDtlExtractor objModDtlExtactor = new MasterModDtlExtractor(masterNode);
		objMasterDtl = objModDtlExtactor.getMasterDtl();
		((MasterModDtl) objMasterDtl).display();
		return objMasterDtl;
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
				System.out.println("attr value" + attrvalue);
				if (attrvalue.equalsIgnoreCase(masterName))
				{
					System.out.println("Inside if");
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

	public String formInitialiseQuery(MasterModDtl _objMasterModDtl, String _hospitalCode)
	{

		String strField = "";
		int len = _objMasterModDtl.getAlFields().size();
		for (int i = 0; i < len; i++)
		{
			strField = strField + _objMasterModDtl.getAlFields().get(i);
			if (i != (len - 1))
			{
				strField = strField + ",";
			}
		}
		String strTable = "";
		String strAlias = "";
		len = _objMasterModDtl.getAlTable().size();
		for (int i = 0; i < len; i++)
		{
			strTable = strTable + _objMasterModDtl.getAlTable().get(i);
			strTable = strTable + " " + _objMasterModDtl.getAlTableAlias().get(i);
			if (i != (len - 1))
			{
				strTable = strTable + ",";
			}
		}

		String Query = "select " + strField + " from " + strTable + " where " + _objMasterModDtl.getGenCondition();

		return Query;
	}//end of form list query*/ 

	private ArrayList buildInitialQuery(MasterModDtl _objMasterModDtl, String[] _chk, String _hospitalCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		for (int i = 0; i < _chk.length; i++)
		{
			System.out.println("_chk" + _chk[i]);
		}
		ArrayList alData = null;
		try
		{
			//System.out.println("buildInitialQuery");

			String Query = formInitialiseQuery(_objMasterModDtl, _hospitalCode);
			for (int i = 0; i < _objMasterModDtl.getAlClause().size(); i++)
			{
				_chk[i] = "'" + _chk[i] + "'";
				String dep = (String) _objMasterModDtl.getAlClause().get(i);
				StringBuffer sb = new StringBuffer(dep);
				int start = sb.lastIndexOf("#");
				StringBuffer sbe = sb.replace(start, start + 3, _chk[i]);
				Query = Query + " " + sbe.toString();
				//System.out.println("Query in first iteration of i" + i);
			}
			Query = setHospitalCode(Query, _objMasterModDtl, _hospitalCode);
			//System.out.println("query::" + Query);
			tx.begin();
			MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
			alData = objMstBuildDb.fetchRecord(Query.trim());
			//System.out.println("alData" + alData);
		}
		catch (Exception e)
		{
			System.out.println("Exception " + e);
			e.printStackTrace();
		}
		finally
		{
			tx.close();
		}
		return alData;

	}

	public ArrayList fetchRecord(String _query)
	{

		System.out.println("_query in fetch record::::" + _query);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ArrayList alDatalist = null;
		try
		{
			tx.begin();
			MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
			alDatalist = objMstBuildDb.fetchRecord(_query);
			//System.out.println("aldataList " + alDatalist);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			tx.close();
			return alDatalist;
		}

	}

	public MasterTO getMasterTO(MasterDtl _objMasterDtl, String _chk, String _activeStatus, MasterTO _objLstMasterTo)
	{
		//System.out.println("inside getmasetrTO");
		String hospitalCode = _objLstMasterTo.getHospitalCode();

		MasterTO objModMasterTO = new MasterModTO();
		objModMasterTO.setHospitalCode(hospitalCode);

		//_chk="102";
		String[] arr = _chk.replace('^', '`').split("`");

		((MasterModTO) objModMasterTO).setChk(arr);
		//System.out.println("sdfkhsdf");
		try
		{

			MasterModDtl objMasterModDtl = (MasterModDtl) _objMasterDtl;
			if (objMasterModDtl.getStaticPage() != null)
			{
				((MasterModTO) objModMasterTO).setObjMstModDtl(objMasterModDtl);
				objModMasterTO.setTblHeading(_objLstMasterTo.getTblHeading());
				objModMasterTO.setTitle(_objLstMasterTo.getTitle());
			}
			else
			{

				//System.out.println("dljkd");
				//objMasterModDtl.display()
				ArrayList alData = buildInitialQuery(objMasterModDtl, arr, hospitalCode);
				((MasterModTO) objModMasterTO).setAlInitialQryResult(alData);
				//System.out.println("objMasterModDtl.getAlControls().size()" + objMasterModDtl.getAlControls().size());
				for (int i = 0; i < objMasterModDtl.getAlControls().size(); i++)
				{
					//System.out.println("inside for ");
					//System.out.println("class name" + objMasterModDtl.getAlControls().get(i).getClass().getName());
					if (objMasterModDtl.getAlControls().get(i).getClass().getName().equals(CLASS_TEXT))
					{
						//System.out.println("inside class text");
						ControlDtl objControlDtl = (ControlDtl) objMasterModDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						TextDtl objtextDtl = (TextDtl) objMasterModDtl.getAlControls().get(i);
						//TextBox objText=processText(objMasterModDtl,objtextDtl,_objLstMasterTo,_formBean);				  
						TextBox objText = processText(objMasterModDtl, objtextDtl, alData);
						objModMasterTO.getControls().add(objText);
					}
					if (objMasterModDtl.getAlControls().get(i).getClass().getName().equals(CLASS_TEXTAREA))
					{
						//System.out.println("inside class Area");
						ControlDtl objControlDtl = (ControlDtl) objMasterModDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						TextAreaDtl objtextareaDtl = (TextAreaDtl) objMasterModDtl.getAlControls().get(i);
						//TextBox objText=processText(objMasterModDtl,objtextDtl,_objLstMasterTo,_formBean);				  
						TextArea objTextArea = processTextArea(objMasterModDtl, objtextareaDtl, alData);
						objModMasterTO.getControls().add(objTextArea);
					}
					if (objMasterModDtl.getAlControls().get(i).getClass().getName().equals(CLASS_COMBO))
					{
						//System.out.println("inside class Combo");
						ControlDtl objControlDtl = (ControlDtl) objMasterModDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						ComboDtl objComboDtl = (ComboDtl) objMasterModDtl.getAlControls().get(i);
						Combo objCombo = processCombo(objMasterModDtl, objComboDtl, alData, objModMasterTO, _activeStatus,_objLstMasterTo);
						objModMasterTO.getControls().add(objCombo);
					}
					if (objMasterModDtl.getAlControls().get(i).getClass().getName().equals(CLASS_RADIOBUTTON))
					{
						//System.out.println("inside class Radio");
						ControlDtl objControlDtl = (ControlDtl) objMasterModDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						RadioDtl objRadioDtl = (RadioDtl) objMasterModDtl.getAlControls().get(i);
						RadioButton objRadio = processRadio(objMasterModDtl, objRadioDtl, alData);
						objModMasterTO.getControls().add(objRadio);
					}
					if (objMasterModDtl.getAlControls().get(i).getClass().getName().equals(CLASS_CHECKBOX))
					{
						//System.out.println("inside class checkbox");
						ControlDtl objControlDtl = (ControlDtl) objMasterModDtl.getAlControls().get(i);
						//System.out.println("CLASS NAME :::" + objControlDtl.getClass().getName());
						CheckBoxDtl objCheckBoxDtl = (CheckBoxDtl) objMasterModDtl.getAlControls().get(i);
						CheckBox objCheckBox = processCheckBox(objMasterModDtl, objCheckBoxDtl, alData);
						objModMasterTO.getControls().add(objCheckBox);
					}
				}
				((MasterModTO) objModMasterTO).setObjMstModDtl(objMasterModDtl);
				//objModMasterTO.setTblHeading(_objLstMasterTo.getTblHeading());		  
				//objModMasterTO.setTitle(_objLstMasterTo.getTitle());	
				//System.out.println("_objLstMasterTo.getTitle()"+objModMasterTO.getTitle());		  			  			  			  
			}
			return objModMasterTO;

		}//end of try
		catch (Exception e)
		{
			System.out.println("Exception " + e);
		}//end of catch 

		return objModMasterTO;
	}//end of method

	public RadioButton processRadio(MasterModDtl _objMasterModDtl, RadioDtl _objRadioDtl, ArrayList _alData)
	{
		String defaultVal = "";
		boolean readOnly = false;
		String alIndex = _objRadioDtl.getDefaultValue().trim();
		defaultVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
		//System.out.println("_objRadioDtl.getDefaultValue()::" + _objRadioDtl.getDefaultValue());
		//System.out.println("defaultVal for radio" + defaultVal);

		if (_objRadioDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
		RadioButton objRadio = new RadioButton(_objRadioDtl.getLabel(), defaultVal, _objRadioDtl.getAlOptionValue(),
				_objRadioDtl.getAlOptionText(), readOnly, _objRadioDtl.getConstraintMap());
		objRadio.setShowOnModify(_objRadioDtl.getShowOnModify());
		objRadio.setShowOnView(_objRadioDtl.getShowOnView());
		objRadio.display();

		return objRadio;
	}

	public CheckBox processCheckBox(MasterModDtl _objMasterModDtl, CheckBoxDtl _objCheckBoxDtl, ArrayList _alData)
	{
		boolean readOnly = true;

		//since the default values for the checkboxes are coming from queryresult
		//and the checkboxes can have multiple values ad default values
		//first the default values from the chk objects dtl are fetched and 
		//put into array by invoking split
		//also the split will only be invoked if there present : in thatdef value  string			 

		String defaultVal = "";
		//String alIndex=_objCheckBoxDtl.getDefaultValue().trim();
		int has = _objCheckBoxDtl.getDefaultValue().lastIndexOf(":");
		if (has != -1)
		{
			String[] defArray = _objCheckBoxDtl.getDefaultValue().split(":");

			for (int i = 0; i < defArray.length; i++)
			{
				if (i == defArray.length - 1)
				{
					defaultVal = defaultVal + (String) _alData.get(Integer.parseInt(defArray[i].trim().substring(2)));
				}
				else
				{
					defaultVal = defaultVal + (String) _alData.get(Integer.parseInt(defArray[i].trim().substring(2))) + ":";
				}
			}
		}

		else
		{
			String alIndex = _objCheckBoxDtl.getDefaultValue().trim();
			defaultVal = defaultVal + (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
		}

		if (_objCheckBoxDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;

		CheckBox objChkBox = new CheckBox(_objCheckBoxDtl.getLabel(), defaultVal, _objCheckBoxDtl.getAlOptionValue(),
				_objCheckBoxDtl.getAlOptionText(), readOnly, _objCheckBoxDtl.getConstraintMap());
		objChkBox.setShowOnModify(_objCheckBoxDtl.getShowOnModify());
		objChkBox.setShowOnView(_objCheckBoxDtl.getShowOnView());
		objChkBox.display();
		return objChkBox;
	}

	public TextBox processText(MasterModDtl _objMasterModDtl, TextDtl _objtextDtl, ArrayList _alData)
	{
		String defaultVal = "";
		String defaultLVal = "";
		boolean readOnly = true;
		TextBox objTextbox = null;
		//System.out.println("aladata::" + _alData);
		//System.out.println("inside process text");

		if (_objtextDtl.getDefaultValue() != null)//case of static
		{
			//System.out.println("case staitic val for text");
			String alIndex = _objtextDtl.getDefaultValue().trim();
			try
			{
				defaultVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
				if (defaultVal == null) defaultVal = "";
			}
			catch (IndexOutOfBoundsException e)
			{
				System.out.println("no data present for this column");
				defaultVal = "";
			}

			//System.out.println("objtextDtl.getDefaultValue()::" + _objtextDtl.getDefaultValue());
			//System.out.println("defaultVal for static" + defaultVal);
		}

		if (_objtextDtl.getLabelSuffix() != null)
		{
			if (_objtextDtl.getLabelSuffix().getDefaultLValue() != null)//case of static
			{
				//System.out.println("case staitic val for text label Suffix");
				defaultLVal = _objtextDtl.getLabelSuffix().getDefaultLValue();
				//System.out.println("objtextDtl.getDefaultValue()::" + _objtextDtl.getLabelSuffix().getDefaultLValue());
				//System.out.println("defaultVal for static" + defaultLVal);
			}
			else
			{
				//System.out.println("case staitic val for text label Suffix");
				String alIndex = _objtextDtl.getLabelSuffix().getDefaultLValue().trim();
				defaultLVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
				//System.out.println("objtextDtl.getDefaultValue()::" + _objtextDtl.getLabelSuffix().getDefaultLValue());
				//System.out.println("defaultVal for static" + defaultLVal);

			}

		}//end of ifof  label suffix		  		   

		if (_objtextDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
		objTextbox = new TextBox(_objtextDtl.getLabel(), defaultVal, _objtextDtl.getConstraintMap(), readOnly);
		objTextbox.setShowOnModify(_objtextDtl.getShowOnModify());
		objTextbox.setShowOnView(_objtextDtl.getShowOnView());
		objTextbox.setLabelSuffix(defaultLVal);
		objTextbox.display();

		return objTextbox;
	}

	public TextArea processTextArea(MasterModDtl _objMasterModDtl, TextAreaDtl _objtextAreaDtl, ArrayList _alData)
	{
		String defaultVal = "";
		boolean readOnly = true;
		TextArea objTextArea = null;
		//System.out.println("inside process textAREA");

		if (_objtextAreaDtl.getDefaultValue() != null)//case of static
		{
			//System.out.println("case staitic val for text area");
			String alIndex = _objtextAreaDtl.getDefaultValue().trim();
			defaultVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
			if (defaultVal == null) defaultVal = "";
			//System.out.println("objtextAreaDtl.getDefaultValue()::" + _objtextAreaDtl.getDefaultValue());
			//System.out.println("defaultVal for static static textAREA" + defaultVal);
		}

		if (_objtextAreaDtl.getReadOnly().equalsIgnoreCase("0")) readOnly = false;
		objTextArea = new TextArea(_objtextAreaDtl.getLabel(), defaultVal, _objtextAreaDtl.getConstraintMap(), readOnly);

		objTextArea.setShowOnModify(_objtextAreaDtl.getShowOnModify());
		objTextArea.setShowOnView(_objtextAreaDtl.getShowOnView());
		objTextArea.display();
		return objTextArea;

	}//end of method processTEXT AREA DTL

	public String getDeftValOfParentCtrlfromSame(int parent, ArrayList _alControls)
	{
		ControlDtl objCtrlDtl = (ControlDtl) _alControls.get(parent);
		String val = objCtrlDtl.getDefaultValue().trim();
		return val;
	}

	public String buildComboQuery(ComboDtl _objComboDtl, MasterTO _objModMasterTO)
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
		String whereClauseHospitalCode = setHospitalCode(comboQuery, _objComboDtl, _objModMasterTO.getHospitalCode());

		comboQuery = whereClauseHospitalCode + _objComboDtl.getGenCondition();
		return comboQuery;
	}

	public Combo processCombo(MasterModDtl _objMasterModDtl, ComboDtl _objComboDtl, ArrayList _alData,
			MasterTO _objModMasterTO, String _activeStatus,MasterTO _objLstMasterTo)
	{

		Combo objCombo = null;
		try
		{
			//first check ifdata to be displayed is static or dynamic
			ArrayList alTxtField = new ArrayList();
			ArrayList alValField = new ArrayList();
			String strComboQuery = "";
			String strcomboQuery = "";
			//ArrayList alControls=_objMasterModDtl.getAlControls();
			ArrayList alDatalist = new ArrayList();
			boolean listDependency = false;
			boolean submit = true;
			String defaultVal = "";
			int parent;
			boolean readOnly = false;
			boolean dependency = false;
			String defaultLVal = "";
			String LabelSuffixQueryforCombo = "";
			ArrayList alcontrol = _objModMasterTO.getControls();

			//System.out.println("inside process COMBO");

			if (_objComboDtl.getAlOptionValues().size() > 0)//if static
			{
				//System.out.println("Combo to be build is static");
				alTxtField = new ArrayList(_objComboDtl.getAlOptiontext());
				alValField = new ArrayList(_objComboDtl.getAlOptionValues());
				//System.out.println("after Combo static");
			}//end of case static
			else
			{//case for dynamic
				if (_objComboDtl.getAlDepCondition().size() == 0)//IF THERE IS NO DEPENDENCY
				{
					dependency = true;
					alTxtField = new ArrayList();
					alValField = new ArrayList();
					//System.out.println(" inside case dynamic");
					strComboQuery = buildComboQuery(_objComboDtl, _objModMasterTO);
					//System.out.println("ComboQuery::" + strComboQuery);
					alDatalist = fetchRecord(strComboQuery);
					for (int k = 0; k < alDatalist.size();)
					{
						alTxtField.add(alDatalist.get(k));
						alValField.add(alDatalist.get(k + 1));
						k = k + 2;
					}
				}//END OF CASE IF THERE IS NO DEPENDENCY
				//IF THERE IS DEPENDENCY ON SAME PAGE
				else
				//deals with case dependecy on same page
				{
					strComboQuery = buildComboQuery(_objComboDtl, _objModMasterTO);
					//System.out.println("strcomboQuery in " + strcomboQuery);

					for (int k = 0; k < _objComboDtl.getAlDepCondition().size(); k++)
					{
						dependency = true;
						String depCondition = (String) _objComboDtl.getAlDepCondition().get(k);
						int idx = depCondition.lastIndexOf("#");
						String dep = depCondition.substring(idx + 1,idx + 2); //dep represents the value after #
						//System.out.println("dep::" + dep);
						StringBuffer sb = new StringBuffer(depCondition);
						int start = sb.lastIndexOf("#");
						//Adding liost page dependency for combo
						String DependentComboQuery="";
						if (dep.equalsIgnoreCase("L"))//if()dependency is on list page 
						{
							parent = getParent(depCondition);
							String val = getDeftValOfParentCtrlfromList(parent, _objLstMasterTo).trim();
							StringBuffer sbe = sb.replace(start, start + 3, "'"+val+"'");
							DependentComboQuery = DependentComboQuery + " " + sbe.toString();
							strComboQuery=strComboQuery+DependentComboQuery;
							listDependency = true; //to prevent strcomboquery execution because if dependency is not in list				 				 			  			  			  	   		   
						}
						else
						{
							parent = getParent(depCondition);
							Control objCtrl = (Control) alcontrol.get(parent);
							if(objCtrl.getDefaultValue()!=null)
							{
								String val = objCtrl.getDefaultValue().trim();
								//System.out.println("val" + val);
								StringBuffer sbe = sb.replace(start, start + 2, val);
								DependentComboQuery = DependentComboQuery + " " + sbe.toString();
								strComboQuery=strComboQuery+DependentComboQuery;
							}
						}
						/*parent = getParent(depCondition);
						//StringBuffer sb = new StringBuffer(depCondition);
						//int start = sb.lastIndexOf("#");
						Control objCtrl = (Control) alcontrol.get(parent);
						String val = objCtrl.getDefaultValue().trim();
						//System.out.println("val" + val);
						StringBuffer sbe = sb.replace(start, start + 2, val);
						strComboQuery = strComboQuery + " " + sbe.toString();*/
						//System.out.println("ComboQuery::" + strComboQuery);
					}
				}

				if (dependency)
				{
					alDatalist = fetchRecord(strComboQuery);
					alTxtField = new ArrayList();
					alValField = new ArrayList();
					//System.out.println("before");
					for (int k = 0; k < alDatalist.size();)
					{
						alTxtField.add(alDatalist.get(k));
						alValField.add(alDatalist.get(k + 1));
						k = k + 2;
					}
				}

			}//end of else ie data for combo is dynamic 
			//code for label-suffix 

			if (_objComboDtl.getLabelSuffix() != null)
			{
				if (_objComboDtl.getLabelSuffix().getDefaultLValue() != null)//case of static
				{
					//System.out.println("case staitic val for text label Suffix");
					String alIndex = _objComboDtl.getLabelSuffix().getDefaultLValue().trim();
					defaultLVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
					//System.out.println("_objComboDtl.getDefaultValue()::" + _objComboDtl.getLabelSuffix().getDefaultLValue());
					//System.out.println("defaultVal for static" + defaultLVal);
				}

			}//end of ifof  label suffix
			if (_objComboDtl.getDefaultValue() == null)
			{
				_objComboDtl.setDefaultValue("-1");
			}
			String alIndex = _objComboDtl.getDefaultValue().trim();
			if (alIndex.substring(1).equals("S"))
			{
				defaultVal = _activeStatus;
			}
			else
			{
				ArrayList alcon = _objModMasterTO.getControls();
				for (int m = 0; m < alcon.size(); m++)
				{
					Control obj = (Control) alcon.get(m);
					//System.out.println("value of control::" + m + " : " + obj.getDefaultValue());
				}
				//System.out.println("_alData::::::::::" + _alData);
				try
				{
					defaultVal = (String) _alData.get(Integer.parseInt(alIndex.trim().substring(2)));
				}
				catch (Exception e)
				{
					defaultVal = "";
				}
				if (defaultVal == null)
				{
					defaultVal = "-1";
				}
				//System.out.println("df val in else::::" + defaultVal);
			}

			if (_objComboDtl.getSubmitRequired().equalsIgnoreCase("0")) submit = false;

			objCombo = new Combo(_objComboDtl.getLabel(), defaultVal, alValField, alTxtField, readOnly, _objComboDtl
					.getConstraintMap());
			objCombo.setLabelSuffix(defaultLVal);
			//System.out.println("before sumdfh");
			objCombo.setSubmitRequired(submit);
			objCombo.setSelectionMandatory(_objComboDtl.getSelectionMandatory());
			objCombo.setShowOnModify(_objComboDtl.getShowOnModify());
			objCombo.setShowOnView(_objComboDtl.getShowOnView().trim());
			//System.out.println("objCombo.setShowOnView" + objCombo.getShowOnView());
			objCombo.display();
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN PROCESS COMBO" + e);
			e.printStackTrace();
		}
		return objCombo;

	}//end of method processCombo

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

	boolean formNewQuery = false;
	boolean formNewLQuery = false;

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
			//System.out.println("val::" + val);//get the default value of parent control
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

	public String buildTextQuery(TextDtl _objtextDtl)
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

	public void reProcessText(Control _objControl, TextDtl _objTextDtl, Object[] _controls, ArrayList _alcontrols)
	{
		String textQuery = "";
		String textLQuery = "";
		String defaultVal = "";
		String defaultLVal = "";

		if (_objTextDtl.getAlDepCondition().size() > 0)//same page dependency for query detail
		{
			//System.out.println("size()>0");

			textQuery = buildTextQuery(_objTextDtl);
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
			MasterTO _objMstModTo)
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
			strComboQuery = buildComboQuery(objComboDtl, _objMstModTo);//form combo query w/o any gen condition
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

	public MasterTO reBuildMod(MasterTO _objMstModTo, hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod)
	{
		try
		{
			//System.out.println("inside rebuilding modify");
			int parent;

			Object[] controls = _formBeanMod.getControls();
			//System.out.println("inside rebuild mod");

			for (int i = 0; i < controls.length; i++)
			{
				//System.out.println("controls " + i + ":" + controls[i]);
			}

			ArrayList alcontrols = _objMstModTo.getControls();
			for (int m = 0; m < alcontrols.size(); m++)
			{
				Control obj = (Control) alcontrols.get(m);
				//System.out.println("value of control::" + m + " : " + obj.getDefaultValue());
			}
			//objAddDtl is used to get dependent condition of controls

			MasterModDtl objModDtl = ((MasterModTO) _objMstModTo).getObjMstModDtl();
			//System.out.println("dfjkhg");
			//System.out.println("objModDtl.getAlControls()" + objModDtl.getAlControls());
			ArrayList alcontrolsDtl = objModDtl.getAlControls();
			//System.out.println("before traversing each control3");
			//for each control in TO
			for (int i = 0; i < alcontrols.size(); i++)
			{
				//System.out.println("inside for");
				//get the present conntrol to work on from TO 
				Control objControl = (Control) alcontrols.get(i);

				if (objControl.getClass() == MasterConfig.CLASS_TEXT)
				{
					TextDtl objTextDtl = (TextDtl) alcontrolsDtl.get(i);
					//objCOntrol represents current control from TO ,CONTROLS IS array OF TYPE OBJECT
					reProcessText(objControl, objTextDtl, controls, alcontrols);
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
					reProcessCombo(objControl, objComboDtl, controls, alcontrols, _objMstModTo);
				}
			}//end of for

		}
		catch (Exception e)
		{
			System.out.println("exception:::" + e);
			e.printStackTrace();
		}
		return _objMstModTo;
	}//end of method rebuild MOd

	private String getDeftValOfParentCtrlfromList(int parent, MasterTO _objLstMasterTo)
	{
		Control objControl = (Control) _objLstMasterTo.getControls().get(parent);
		String val = objControl.getDefaultValue();//this val is used for where clause
		return val;
	}

	private String getValueString(String _str, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod, ArrayList _alInitQryResult)
	{
		//System.out.println("inside getValueString");
		String valString = "";

		//int len=_objMstModDtl.getAlValueMapUQ().size();	
		//System.out.println("inside getValueString2");
		Object[] ctrlArray = _formBeanMod.getControls();
		//System.out.println("inside getValueString3");
		//System.out.println("ctrlArray len" + ctrlArray.length);

		String ctrl = _str.substring(1);
		if (ctrl.equalsIgnoreCase("entryDate"))
		{
			//System.out.println("inside entrydate");
			valString = "sysdate";
		}
		else if (ctrl.equalsIgnoreCase("seatid"))
		{
			//System.out.println("inside seatid");
			valString = _objLstMstTo.getSeatId();
		}
		else if (ctrl.equalsIgnoreCase("defaultZero")) ////seating default value 0
		{
			valString = valString + " 0 ";
		}

		else if (ctrl.equalsIgnoreCase("defaultOne")) ////seating default value 1
		{
			//System.out.println("inside defaultOne");

			valString = valString + " 1 ";

		}
		else if (ctrl.equalsIgnoreCase("defaultTwo")) ////seating default value 2
		{
			//System.out.println("inside defaultOne");

			valString = valString + " 2 ";

		}
		else if (_str.substring(1, 2).equalsIgnoreCase("Q"))//check if insertion value is dependent on list page
		{
			//get the control on which it dependent depcontrol represents this control
			//System.out.println("ctrl.substring(2,3)"+strControl.substring(2,3));

			int queryVal = Integer.parseInt(_str.substring(2, 3));
			//get the default value of this control
			String val = (String) _alInitQryResult.get(queryVal);
			valString = valString + "'" + val + "'";
		}
		else if (_str.substring(1, 2).equalsIgnoreCase("I"))//check if value is to be inserted in initcap
		{
			//get the control on which it dependent depcontrol represents this control
			//System.out.println("ctrl.substring(2,3)" + _str.substring(2, 3));

			int InitCtrl = Integer.parseInt(_str.substring(2, 3));
			valString = valString + "initcap('" + (String) ctrlArray[InitCtrl] + "')";

		}
		else if (_str.substring(1, 2).equalsIgnoreCase("U"))//check if value is to be inserted in upper case
		{
			//get the control on which it dependent depcontrol represents this control
			//System.out.println("ctrl.substring(2,3)" + _str.substring(2, 3));

			int upperCtrl = Integer.parseInt(_str.substring(2, 3));
			//get the default value of this control

			valString = valString + "upper('" + (String) ctrlArray[upperCtrl] + "')";

		}
		else
		{
			//System.out.println("ctrl::" + ctrl);
			int ctrll = Integer.parseInt(ctrl);
			valString = "'" + (String) ctrlArray[ctrll] + "'";
		}
		return valString;
	}

	private String getSETValues(MasterModDtl _objMstModDtl, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod, ArrayList _alInitQryResult)
	{
		ArrayList alFields = _objMstModDtl.getAlFieldsUQ();
		ArrayList alValueMap = _objMstModDtl.getAlValueMapUQ();

		int len = alFields.size();
		String valStr = "";
		for (int i = 0; i < alFields.size(); i++)
		{
			if (i == len - 1)
			{
				valStr = valStr + alFields.get(i) + "="
						+ getValueString((String) alValueMap.get(i), _objLstMstTo, _formBeanMod, _alInitQryResult);
			}
			else
			{
				valStr = valStr + alFields.get(i) + "="
						+ getValueString((String) alValueMap.get(i), _objLstMstTo, _formBeanMod, _alInitQryResult) + ",";
			}

		}
		return valStr;

	}

	private String formWhereCond(ArrayList _alDep, MasterTO _objMstModTo)
	{
		String whereCond = "";
		int len = _alDep.size();
		String[] arr = ((MasterModTO) _objMstModTo).getChk();
		//System.out.println("lDep.size()" + len);
		//System.out.println("ArrayLength" + arr.length);

		for (int i = 0; i < _alDep.size(); i++)
		{
			String dep = (String) _alDep.get(i);
			StringBuffer sb = new StringBuffer(dep);
			int start = sb.lastIndexOf("#");
			//System.out.println("  arr[i] before" + arr[i]);
			//arr[i]="'"+arr[i]+"'";	
			//System.out.println("  arr[i]" + arr[i]);
			StringBuffer sbe = sb.replace(start, start + 3, arr[i]);
			if (i == len - 1)
			{
				whereCond = whereCond + sbe.toString(); /////changes to build query with primary key in quotes////
				//System.out.println("whereCond:::" + whereCond);
			}
			else
			{
				whereCond = whereCond + sbe.toString() + " AND ";
			}
		}
		return whereCond;
	}
	
	private String formRedundentWhereCond(ArrayList _alDep, MasterTO _objMstModTo)
	{
		String whereCond = "";
		int len = _alDep.size();
		String[] arr = ((MasterModTO) _objMstModTo).getChk();
		//System.out.println("lDep.size()" + len);
		//System.out.println("ArrayLength" + arr.length);

		for (int i = 0; i < _alDep.size(); i++)
		{
			String dep = (String) _alDep.get(i);
			StringBuffer sb = new StringBuffer(dep);
			int start = sb.lastIndexOf("#");
			//System.out.println("  arr[i] before" + arr[i]);
			//arr[i]="'"+arr[i]+"'";	
			//System.out.println("  arr[i]" + arr[i]);
			StringBuffer sbe = sb.replace(start, start + 3, arr[i]);
			
				whereCond = " AND " +whereCond + sbe.toString() ;
			
		}
		whereCond=whereCond.replaceAll("=","<>");
		return whereCond;
	}

	private String formUpdateQuery(MasterTO _objMstModTo, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod)
	{
		MasterModDtl objMstModDtl = ((MasterModTO) _objMstModTo).getObjMstModDtl();

		ArrayList alDep = objMstModDtl.getConditionUQ();
		ArrayList alInitQryResult = ((MasterModTO) _objMstModTo).getAlInitialQryResult();

		String query = "UPDATE " + objMstModDtl.getTableUQ() + " SET "
				+ getSETValues(objMstModDtl, _objLstMstTo, _formBeanMod, alInitQryResult) + " where "
				+ formWhereCond(alDep, _objMstModTo);;

		if (objMstModDtl.getHospitalCode() != null)
		{
			query = query + " AND " + objMstModDtl.getHospitalCodeUQ() + "=" + _objLstMstTo.getHospitalCode();
		}

		System.out.println("update query:::" + query);
		return query;
	}
	
	private String formRedundentQuery(MasterTO _objMstModTo, MasterTO _objLstMstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanAdd)
	{
		
		MasterModDtl objMstModDtl = ((MasterModTO) _objMstModTo).getObjMstModDtl();
		//String colummns = getColumnString(objMstAddDtl);
		String query ="";
		ArrayList alDep = objMstModDtl.getConditionUQ();
		query = objMstModDtl.getRedundentQuery();
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
		query=query+" trim(upper('"+ctrlArray[index]+"'))";
		
		///check if query contians hospital code check else add by default
		int hospitalCodeIndex=query.toUpperCase().indexOf("GNUM_HOSPITAL_CODE");
		
		if(hospitalCodeIndex==-1)
		query=query+" and gnum_hospital_code ="+_objLstMstTo.getHospitalCode();
		
		query=query+formRedundentWhereCond(alDep, _objMstModTo);
		
		}
		System.out.println("redundent query:::" + query);
		return query;
	}

	String update(MasterTO _objMstModTo, MasterTO _objLstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod)
	{
		//System.out.println("IN update OF MOD BUILDER");
		boolean recordupdated = false;
		int isRedundent=0;
		String statusMessage="FALSE";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			String updateQuery = formUpdateQuery(_objMstModTo, _objLstTo, _formBeanMod);
			String redundentQuery=formRedundentQuery(_objMstModTo, _objLstTo, _formBeanMod);
			System.out.println("updateQuery" + updateQuery);
			MasterBuilderDb objMstBuildDb = new MasterBuilderDb(tx);
			if(!redundentQuery.equals(""))
			{
			isRedundent=objMstBuildDb.redundentCheck(redundentQuery);
			}
			if(isRedundent==0)
			{
			recordupdated = objMstBuildDb.updateRecord(updateQuery);
			}
			//System.out.println("after modify");
		}
		catch (Exception e)
		{
			System.out.println("exception ::" + e);
			e.printStackTrace();
		}
		finally
		{
			if(isRedundent>=1)
			{
				statusMessage="DUPLICATE";
			}
			else
			{
				if(recordupdated==true)
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
		//System.out.println("recordSaved::"+recordSaved);
		return statusMessage;
	}

	private String setHospitalCode(String _query, ComboDtl _objComboDtl, String _hospitalCode)
	{

		if (_objComboDtl.getHospitalCode() != null)
		{
			_query = _query + " " + _objComboDtl.getHospitalCode() + "=" + _hospitalCode;
		}
		return _query;
	}

	private String setHospitalCode(String _query, MasterModDtl _objMasterModDtl, String _hospitalCode)
	{

		if (_objMasterModDtl.getHospitalCode() != null)
		{
			_query = _query + " AND " + _objMasterModDtl.getHospitalCode() + "=" + _hospitalCode;
		}
		return _query;
	}

}//end of class
