package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl;
import hisglobal.masterxml.masterworkshop.tools.ComboDtl;
import hisglobal.masterxml.masterworkshop.tools.ControlDtlWithSuffix;
import hisglobal.masterxml.masterworkshop.tools.MasterDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterModDtl;
import hisglobal.masterxml.masterworkshop.tools.RadioDtl;
import hisglobal.masterxml.masterworkshop.tools.TextAreaDtl;
import hisglobal.masterxml.masterworkshop.tools.TextDtl;

import java.util.*;
import org.w3c.dom.*;

public class MasterModTreeTraverser extends MasterTreeTraverser
{
	public MasterModTreeTraverser(Node _rootOfTree, MasterDtl _objMasterDtl)
	{
		super.rootOfTree = _rootOfTree;
		super.objMasterDtl = _objMasterDtl;
		//System.out.println("object:::" + super.objMasterDtl);
	}//end of constructor

	public void processor(Stack _nodeHierarchyDetail, NamedNodeMap _nodeMap)
	{
		int x = 0;

		//System.out.println("inside  attr ");
		//System.out.println("_nodeMap.getLength()" + _nodeMap.getLength());
		//System.out.println("STack::::value inside pro(attr)" + _nodeHierarchyDetail);
		for (int i = 0; i < _nodeMap.getLength(); i++)
		{
			Node nd = _nodeMap.item(i);
			//System.out.println("nd.getNodeName()" + nd.getNodeName());
			//System.out.println("nd.getnodee value()" + nd.getNodeValue());

		}
		Node nd = _nodeMap.item(0);
		//System.out.println("nd.getNodeValue" + nd.getNodeValue());
		//System.out.println("_nodeHierarchyDetail.size())" + _nodeHierarchyDetail.size());
		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("modify-page")) if (((String) _nodeHierarchyDetail
				.get(1)).equalsIgnoreCase("dynamic-page"))
		{
			if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("controls"))
			{
				if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("control"))
				{
					if (_nodeHierarchyDetail.size() == 4)
					{
						//System.out.println("inside if(_nodeHierarchyDetail.size()==4)");

						for (int i = 0; i < _nodeMap.getLength(); i++)
						{

							//System.out.println("inside forsss" + i);
							nd = _nodeMap.item(i);
							//System.out.println("nd.getNodeName()" + nd.getNodeName());
							//System.out.println("nd.getnodee value()" + nd.getNodeValue());
							String nodename = nd.getNodeName();
							if (nodename.equalsIgnoreCase("type"))
							{
								String controlType = nd.getNodeValue();
								if (controlType.equalsIgnoreCase("combo"))
								{
									//System.out.println("create an object of control combo");
									ComboDtl objComboDtl = new ComboDtl();
									ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
									alControls.add(objComboDtl);
									if (i == 0) x = 1;
									else x = 0;
									Node ndee = _nodeMap.item(x);
									objComboDtl.setIndex(ndee.getNodeValue());
									System.out.println("objComboDtl.setIndex" + objComboDtl.getIndex());
								}
								if (controlType.equalsIgnoreCase("text"))
								{
									//System.out.println("create an object of control text");
									TextDtl objTextDtl = new TextDtl();
									ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
									alControls.add(objTextDtl);
									if (i == 0) x = 1;
									else x = 0;
									Node ndee = _nodeMap.item(x);
									objTextDtl.setIndex(ndee.getNodeValue());
									//System.out.println("objTextDtl.setIndex" + objTextDtl.getIndex());
								}
								if (controlType.equalsIgnoreCase("textarea"))
								{
									//System.out.println("create an object of control text area");
									TextAreaDtl objTextAreaDtl = new TextAreaDtl();
									ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
									alControls.add(objTextAreaDtl);
									if (i == 0) x = 1;
									else x = 0;
									Node ndee = _nodeMap.item(x);
									objTextAreaDtl.setIndex(ndee.getNodeValue());
									//System.out.println("objTextAreaDtl.setIndex" + objTextAreaDtl.getIndex());
								}
								if (controlType.equalsIgnoreCase("radio"))
								{
									//System.out.println("create an object of control radio");
									RadioDtl objRadioDtl = new RadioDtl();
									ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
									alControls.add(objRadioDtl);
									if (i == 0) x = 1;
									else x = 0;
									Node ndee = _nodeMap.item(x);
									objRadioDtl.setIndex(ndee.getNodeValue());
									//System.out.println("objRadioDtl.setIndex" + objRadioDtl.getIndex());
								}
								if (controlType.equalsIgnoreCase("checkbox"))
								{
									//System.out.println("create an object of control checkbox");
									CheckBoxDtl objCheckBoxDtl = new CheckBoxDtl();
									ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
									alControls.add(objCheckBoxDtl);
									if (i == 0) x = 1;
									else x = 0;
									Node ndee = _nodeMap.item(x);
									objCheckBoxDtl.setIndex(ndee.getNodeValue());
									//System.out.println("objCheckBoxDtl.setIndex" + objCheckBoxDtl.getIndex());
								}

							}//if(nodename.equalsIgnoreCase("control"))							    	
						}
					}//if(_nodeHierarchyDetail.size()==4)
					else
					{
						//System.out.println("inside else");
						ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
						Object obj = alControls.get(alControls.size() - 1);
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(7))
										.equalsIgnoreCase("table"))
								{
									((ComboDtl) obj).getAlTableAlias().add(nd.getNodeValue());
									//System.out.println("table alias combo::" + ((ComboDtl) obj).getAlTableAlias());
								}
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("option"))
								{
									((ComboDtl) obj).getAlOptionValues().add(nd.getNodeValue());
									//System.out.println(" static combo ot val::" + ((ComboDtl) obj).getAlOptionValues());

								}

							}//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label-suffix")) if (((String) _nodeHierarchyDetail
									.get(5)).equalsIgnoreCase("data")) if (((String) _nodeHierarchyDetail.get(6))
									.equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail.get(7))
									.equalsIgnoreCase("query-detail")) if (((String) _nodeHierarchyDetail.get(8))
									.equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(9))
									.equalsIgnoreCase("table"))
							{
								//System.out.println("setting table alias");
								((ComboDtl) obj).getLabelSuffix().getAlLTableAlias().add(nd.getNodeValue());
								//((ComboDtl)obj).getAlLTableAlias().add(nd.getNodeValue());
								//System.out.println("((TextDtl)obj).getAlTableAlias()"+((ComboDtl)obj).getAlLTableAlias());													
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXT_DTL))
						{
							//System.out.println("case text setting con");
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("constraints"))
							{
								((TextDtl) obj).setConstraintMap(new HashMap());
								for (int i = 0; i < _nodeMap.getLength(); i++)
								{
									Node nde = _nodeMap.item(i);
									if (nde.getNodeName().equalsIgnoreCase("size"))
									{
										//System.out.println("inside con size");
										((TextDtl) obj).getConstraintMap().put("size", nde.getNodeValue());
										//System.out.println("getConstraintMap size" + ((TextDtl) obj).getConstraintMap());
									}

									if (nde.getNodeName().equalsIgnoreCase("maxlength"))
									{
										//System.out.println("inside con max");
										((TextDtl) obj).getConstraintMap().put("maxlength", nde.getNodeValue());
										//System.out.println("getConstraintMap:: max" + ((TextDtl) obj).getConstraintMap());
									}
									if (nde.getNodeName().equalsIgnoreCase("inputType"))
									{
										//System.out.println("inside con input");
										((TextDtl) obj).getConstraintMap().put("inputType", nde.getNodeValue());
										//System.out.println("getConstraintMap input type"+ ((TextDtl) obj).getConstraintMap());
									}
									if (nde.getNodeName().equalsIgnoreCase("mandatory"))
									{
										//System.out.println("inside con mandatory");
										((TextDtl) obj).getConstraintMap().put("mandatory", nde.getNodeValue());
										//System.out.println("getConstraintMap mandatory" + ((TextDtl) obj).getConstraintMap());
									}

									if (nde.getNodeName().equalsIgnoreCase("datepicker"))
									{
										//System.out.println("inside con datepicker");
										((TextDtl) obj).getConstraintMap().put("datepicker", nde.getNodeValue());
										//System.out.println("getConstraintMap datepicker"+ ((TextDtl) obj).getConstraintMap());
									}
									if (nde.getNodeName().equalsIgnoreCase("methodstring"))
									{
										//System.out.println("inside con methodstring");
										((TextDtl) obj).getConstraintMap().put("methodstring", nde.getNodeValue());
										//System.out.println("getConstraintMap methodstring"+ ((TextDtl) obj).getConstraintMap());
									}
								}//for(int i=0;i<_nodeMap.getLength();i++)
							}//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("constraints"))

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("query-detail")) if (((String) _nodeHierarchyDetail.get(7))
										.equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(8))
										.equalsIgnoreCase("table"))
								{
									((TextDtl) obj).getAlTableAlias().add(nd.getNodeValue());
									//System.out.println("table alias TEXT::" + ((TextDtl) obj).getAlTableAlias());
								}
							}

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label-suffix")) if (((String) _nodeHierarchyDetail
									.get(5)).equalsIgnoreCase("data")) if (((String) _nodeHierarchyDetail.get(6))
									.equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail.get(7))
									.equalsIgnoreCase("query-detail")) if (((String) _nodeHierarchyDetail.get(8))
									.equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(9))
									.equalsIgnoreCase("table"))
							{
								//System.out.println("setting table alias for TEXT label-suffix");
								((TextDtl) obj).getLabelSuffix().getAlLTableAlias().add(nd.getNodeValue());
								//((TextDtl)obj).getAlLTableAlias().add(nd.getNodeValue());
								//System.out.println("((TextDtl)obj).getAlTableAlias()"+((TextDtl)obj).getAlLTableAlias());													
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXT_DTL))
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXTAREA_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("constraints"))
							{
								((TextAreaDtl) obj).setConstraintMap(new HashMap());

								for (int i = 0; i < _nodeMap.getLength(); i++)
								{
									Node nde = _nodeMap.item(i);
									if (nde.getNodeName().equalsIgnoreCase("styleString"))
									{
										//System.out.println("inside con width");
										((TextAreaDtl) obj).getConstraintMap().put("styleString", nde.getNodeValue());
										//System.out.println("getConstraintMap col" + ((TextAreaDtl) obj).getConstraintMap());
									}

									if (nde.getNodeName().equalsIgnoreCase("mandatory"))
									{
										//System.out.println("inside con mandatory");
										((TextAreaDtl) obj).getConstraintMap().put("mandatory", nde.getNodeValue());
										//System.out.println("getConstraintMap mandatory"+ ((TextAreaDtl) obj).getConstraintMap());
									}
									if (nde.getNodeName().equalsIgnoreCase("methodstring"))
									{
										//System.out.println("inside con methodstring");
										((TextAreaDtl) obj).getConstraintMap().put("methodstring", nde.getNodeValue());
										//System.out.println("getConstraintMap methodstring"+ ((TextAreaDtl) obj).getConstraintMap());
									}
									if (nde.getNodeName().equalsIgnoreCase("inputType"))
									{
										//System.out.println("inside con input");
										((TextAreaDtl) obj).getConstraintMap().put("inputType", nde.getNodeValue());
										//System.out.println("getConstraintMap input type"+ ((TextAreaDtl) obj).getConstraintMap());
									}
								}//for(int i=0;i<_nodeMap.getLength();i++)
							}//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("constraints"))for TEXT AREA

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("query-detail")) if (((String) _nodeHierarchyDetail.get(7))
										.equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(8))
										.equalsIgnoreCase("table"))
								{
									((TextAreaDtl) obj).getAlTableAlias().add(nd.getNodeValue());
									//System.out.println("table alias TEXTAREA::" + ((TextAreaDtl) obj).getAlTableAlias());
								}
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXTAREA_DTL))
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_RADIOBUTTON_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data")) if (((String) _nodeHierarchyDetail
									.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail.get(6))
									.equalsIgnoreCase("option"))
							{
								((RadioDtl) obj).getAlOptionValue().add(nd.getNodeValue());
								//System.out.println(" static radio val::" + ((RadioDtl) obj).getAlOptionValue());
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_RAdioBUTTON_DTL))
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_CHECKBOX_DTL))
						{
							//System.out.println("inside check attr");
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data")) if (((String) _nodeHierarchyDetail
									.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail.get(6))
									.equalsIgnoreCase("option"))
							{
								((CheckBoxDtl) obj).getAlOptionValue().add(nd.getNodeValue());
								//System.out.println(" static checkbox val::" + ((CheckBoxDtl) obj).getAlOptionValue());
							}
						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_RAdioBUTTON_DTL))	

					}//end of else
				}//if(((String)_nodeHierarchyDetail.get(3)).equalsIgnoreCase("control"))
			}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("controls"))
			if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("initialise-query")) if (((String) _nodeHierarchyDetail
					.get(3)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(4))
					.equalsIgnoreCase("table"))
			{
				((MasterModDtl) objMasterDtl).getAlTableAlias().add(nd.getNodeValue());
				// System.out.println("MasterModDtl)objMasterDtl).getAlValueMap"+((MasterModDtl)objMasterDtl).getAlValueMap());
			}

			if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("update-query")) if (((String) _nodeHierarchyDetail
					.get(3)).equalsIgnoreCase("fields"))
			{
				((MasterModDtl) objMasterDtl).getAlValueMapUQ().add(nd.getNodeValue());
				// System.out.println("MasterModDtl)objMasterDtl).getAlValueMap"+((MasterModDtl)objMasterDtl).getAlValueMap());
			}
			if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("redundent-check")) 
			{
				((MasterModDtl) objMasterDtl).getAlRedundentQuery().add(nd.getNodeValue());
				// System.out.println("MasterModDtl)objMasterDtl).getAlValueMap"+((MasterModDtl)objMasterDtl).getAlValueMap());
			}

		}// if(((String)_nodeHierarchyDetail.get(1)).equalsIgnoreCase("dynamic"))

	}///end of method processor(Stack _nodeHierarchyDetail, NamedNodeMap  _nodeMap)

	public void processor(Stack _nodeHierarchyDetail, Node _textNode)
	{
		//System.out.println("text node " + _textNode.getNodeValue());
		//System.out.println("satck values in process text node" + _nodeHierarchyDetail);

		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("modify-page"))
		{
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("static-page"))
			{
				((MasterModDtl) objMasterDtl).setStaticPage(_textNode.getNodeValue());
			}
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("dynamic-page"))
			{
				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("controls"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("control"))
					{
						ArrayList alControls = ((MasterModDtl) objMasterDtl).getAlControls();
						//System.out.println("alControls.size()" + alControls.size());
						Object obj = alControls.get(alControls.size() - 1);

						if (obj.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label"))
							{
								((ComboDtl) obj).setLabel(_textNode.getNodeValue());
								//System.out.println("(ComboDtl)obj).getLabel" + ((ComboDtl) obj).getLabel());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("selection-mandatory"))
							{
								((ComboDtl) obj).setSelectionMandatory(_textNode.getNodeValue().trim());
								//System.out.println("(ComboDtl)obj).getLabel" + ((ComboDtl) obj).getSelectionMandatory());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("submitRequired"))
							{
								((ComboDtl) obj).setSubmitRequired(_textNode.getNodeValue());
								//System.out.println("(ComboDtl)obj).setSubmitRequired" + ((ComboDtl) obj).getSubmitRequired());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-modify-page"))
							{
								((ComboDtl) obj).setShowOnModify(_textNode.getNodeValue());
								//System.out.println("((TextDtl)obj).setReadOnly"+((TextDtl)obj).getShowOnModify());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-view-page"))
							{
								((ComboDtl) obj).setShowOnView(_textNode.getNodeValue());
								//System.out.println("((TextDtl)obj).setReadOnly"+((TextDtl)obj).getShowOnModify());
							}

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic"))
								{
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("options"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("text-field"))
										{
											((ComboDtl) obj).setTextField(_textNode.getNodeValue());
											//System.out.println("((ComboDtl)obj).setTextField"+ ((ComboDtl) obj).getTextField());

										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("value-field"))
										{
											((ComboDtl) obj).setValueField(_textNode.getNodeValue());
											//System.out.println("((ComboDtl)obj).setvalueField"+ ((ComboDtl) obj).getValueField());
										}

									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("tables"))
									{
										((ComboDtl) obj).getAlTable().add(_textNode.getNodeValue());
										//System.out.println("((ComboDtl)obj).getAlTable" + ((ComboDtl) obj).getAlTable());
									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("condition"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("general"))
										{
											((ComboDtl) obj).setGenCondition(_textNode.getNodeValue());
											//System.out.println("((ComboDtl)obj).setGenCondition"+ ((ComboDtl) obj).getGenCondition());

										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("hospital-code"))
										{
											((ComboDtl) obj).setHospitalCode(_textNode.getNodeValue());
											//System.out.println("((ComboDtl)obj).setGenCondition"+ ((ComboDtl) obj).getHospitalCode());

										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("dependent")) if (((String) _nodeHierarchyDetail
												.get(8)).equalsIgnoreCase("clause"))
										{
											((ComboDtl) obj).getAlDepCondition().add(_textNode.getNodeValue());
											//System.out.println("((ComboDtl)obj).setdepCondition"+ ((ComboDtl) obj).getAlDepCondition());
										}
									}//if(((String)_nodeHierarchyDetail.get(6)).equalsIgnoreCase("condition"))															

								}
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("option"))
								{
									((ComboDtl) obj).getAlOptiontext().add(_textNode.getNodeValue());
									//System.out.println("((ComboDtl)obj).getAlOptiontext()::"+ ((ComboDtl) obj).getAlOptiontext());
								}

							}//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("default"))
							{
								((ComboDtl) obj).setDefaultValue(_textNode.getNodeValue());
								//System.out.println("(ComboDtl)obj).setDefaultValue" + ((ComboDtl) obj).getDefaultValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label-suffix"))
							{

								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("data"))
								{
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("dynamic"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("query-detail"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("fields"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("field"))
												{
													((ComboDtl) obj).setLabelSuffix(new ControlDtlWithSuffix());
													((ComboDtl) obj).getLabelSuffix().getAlLField().add(
															_textNode.getNodeValue());
												}

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("tables"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("table"))
												{
													((ComboDtl) obj).getLabelSuffix().getAlLTable().add(
															_textNode.getNodeValue());
												}

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("condition"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("general"))
												{
													((ComboDtl) obj).getLabelSuffix().setGenLCondition(
															_textNode.getNodeValue());
												}
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("dependent")) if (((String) _nodeHierarchyDetail
														.get(10)).equalsIgnoreCase("clause"))
												{
													((ComboDtl) obj).getLabelSuffix().getAldepLcondition().add(
															_textNode.getNodeValue());
												}

											}//if(((String)_nodeHierarchyDetail.get(8)).equalsIgnoreCase("condition"))

										}
										if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("value-detail"))
										{
											if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("dependent"))
											{
												((ComboDtl) obj).getLabelSuffix().getAldepLconditionVal().add(
														_textNode.getNodeValue());
											}

										}//label suffix comboif(((String)_nodeHierarchyDetail.get(8)).equalsIgnoreCase("value-detail"))								
									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("static"))
									{
										((ComboDtl) obj).setLabelSuffix(new ControlDtlWithSuffix());
										((ComboDtl) obj).getLabelSuffix().setDefaultLValue(_textNode.getNodeValue());

									}

								}//for label suffix if(((String)_nodeHierarchyDetail.get(5)).equalsIgnoreCase("data"))							
							}//end of label suffix combo						
						}///if (obj.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))

						if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXT_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label"))
							{
								((TextDtl) obj).setLabel(_textNode.getNodeValue());
								//System.out.println("(TextDtl)obj).getLabel" + ((TextDtl) obj).getLabel());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic"))
								{
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("query-detail"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("fields"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("field"))
											{
												((TextDtl) obj).getAlField().add(_textNode.getNodeValue());
												//System.out.println("(TextDtl)obj).getAlField" + ((TextDtl) obj).getAlField());
											}

										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("tables"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("table"))
											{
												((TextDtl) obj).getAlTable().add(_textNode.getNodeValue());
												//System.out.println("(TextDtl)obj).getAlTable" + ((TextDtl) obj).getAlTable());

											}
										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("condition"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("general"))
											{
												((TextDtl) obj).setGenCondition(_textNode.getNodeValue());
												//System.out.println("(TextDtl)obj).setGenCondition"+ ((TextDtl) obj).getGenCondition());

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("dependent"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("clause"))
												{
													((TextDtl) obj).getAlDepCondition().add(_textNode.getNodeValue());
													//System.out.println("(TextDtl)obj).getAldepcondition"+ ((TextDtl) obj).getAlDepCondition());
												}
											}
										}
									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("value-detail"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("dependent")) ;
										{
											((TextDtl) obj).getAlDepConditionVal().add(_textNode.getNodeValue());
											//System.out.println("(TextDtl)obj).getAldepconditionVal"+ ((TextDtl) obj).getAlDepConditionVal());

										}
									}

								}// for TEXT if(((String)_nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic"))																					
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static"))
								{
									((TextDtl) obj).setDefaultValue(_textNode.getNodeValue());
								}

							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("readonly"))
							{
								((TextDtl) obj).setReadOnly(_textNode.getNodeValue());
								//System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getReadOnly());
							}

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-modify-page"))
							{
								((TextDtl) obj).setShowOnModify(_textNode.getNodeValue());
								//System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-view-page"))
							{
								((TextDtl) obj).setShowOnView(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}

							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label-suffix"))
							{
								/*if (_nodeHierarchyDetail.size()==5)
								 {
								System.out.println("create an object of label suffix:");
								((TextDtl)obj).setLabelSuffix(new  ControlDtlWithSuffix());
								System.out.println("((TextDtl)obj).getLabelSUffix"+((TextDtl)obj).getLabelSuffix());
								 }*/
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("data"))
								{
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("dynamic"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("query-detail"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("fields"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("field"))
												{

													((TextDtl) obj).setLabelSuffix(new ControlDtlWithSuffix());
													((TextDtl) obj).getLabelSuffix().getAlLField().add(
															_textNode.getNodeValue());
												}

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("tables"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("table"))
												{
													((TextDtl) obj).getLabelSuffix().getAlLTable().add(
															_textNode.getNodeValue());
												}

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("condition"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("general"))
												{
													((TextDtl) obj).getLabelSuffix().setGenLCondition(
															_textNode.getNodeValue());
												}
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("dependent")) if (((String) _nodeHierarchyDetail
														.get(10)).equalsIgnoreCase("clause"))
												{
													((TextDtl) obj).getLabelSuffix().getAldepLcondition().add(
															_textNode.getNodeValue());

												}

											}//if(((String)_nodeHierarchyDetail.get(8)).equalsIgnoreCase("condition"))

										}
										if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("value-detail"))
										{
											if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("dependent"))
											{
												((TextDtl) obj).getLabelSuffix().getAldepLconditionVal().add(
														_textNode.getNodeValue());
											}

										}//label suffix comboif(((String)_nodeHierarchyDetail.get(8)).equalsIgnoreCase("value-detail"))								
									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("static"))
									{
										((TextDtl) obj).setLabelSuffix(new ControlDtlWithSuffix());
										((TextDtl) obj).getLabelSuffix().setDefaultLValue(_textNode.getNodeValue());

									}

								}//for label suffix if(((String)_nodeHierarchyDetail.get(5)).equalsIgnoreCase("data"))							
							}//end of label suffix TEXT					

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXT_DTL))

						if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXTAREA_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label"))
							{
								((TextAreaDtl) obj).setLabel(_textNode.getNodeValue());
								System.out.println("(TextAreaDtl)obj).getLabel" + ((TextAreaDtl) obj).getLabel());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic"))
								{
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("query-detail"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("fields"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("field"))
											{
												((TextAreaDtl) obj).getAlField().add(_textNode.getNodeValue());
												System.out.println("(TextAreaDtl)obj).getAlField"
														+ ((TextAreaDtl) obj).getAlField());
											}

										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("tables"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("table"))
											{
												((TextAreaDtl) obj).getAlTable().add(_textNode.getNodeValue());
												System.out.println("(TextAreaDtl)obj).getAlTable"
														+ ((TextAreaDtl) obj).getAlTable());

											}
										}
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("condition"))
										{
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("general"))
											{
												((TextAreaDtl) obj).setGenCondition(_textNode.getNodeValue());
												System.out.println("(TextAreaDtl)obj).setGenCondition"
														+ ((TextAreaDtl) obj).getGenCondition());

											}
											if (((String) _nodeHierarchyDetail.get(8)).equalsIgnoreCase("dependent"))
											{
												if (((String) _nodeHierarchyDetail.get(9)).equalsIgnoreCase("clause"))
												{
													((TextAreaDtl) obj).getAlDepCondition().add(_textNode.getNodeValue());
													System.out.println("(TextAreaDtl)obj).getAldepcondition"
															+ ((TextAreaDtl) obj).getAlDepCondition());
												}
											}
										}

									}
									if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("value-detail"))
									{
										if (((String) _nodeHierarchyDetail.get(7)).equalsIgnoreCase("dependent")) ;
										{
											System.out.println("inside dependent of text area val");
											((TextAreaDtl) obj).getAlDepConditionVal().add(_textNode.getNodeValue());
											System.out.println("(TextAreaDtl)obj).getAldepconditionVal"
													+ ((TextAreaDtl) obj).getAlDepConditionVal());

										}
									}

								}// for TEXT if(((String)_nodeHierarchyDetail.get(5)).equalsIgnoreCase("dynamic"))																					
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static"))
								{
									((TextAreaDtl) obj).setDefaultValue(_textNode.getNodeValue());
								}

							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("readonly"))
							{
								((TextAreaDtl) obj).setReadOnly(_textNode.getNodeValue());
								System.out.println("((TextAreaDtl)obj).setReadOnly" + ((TextAreaDtl) obj).getReadOnly());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-modify-page"))
							{
								((TextAreaDtl) obj).setShowOnModify(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-view-page"))
							{
								((TextAreaDtl) obj).setShowOnView(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_TEXTAREA_DTL))						
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_RADIOBUTTON_DTL))
						{
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label"))
							{
								((RadioDtl) obj).setLabel(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("option"))
								{
									((RadioDtl) obj).getAlOptionText().add(_textNode.getNodeValue());
								}
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("default"))
							{
								((RadioDtl) obj).setDefaultValue(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("readonly"))
							{
								((RadioDtl) obj).setReadOnly(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-modify-page"))
							{
								((RadioDtl) obj).setShowOnModify(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-view-page"))
							{
								((RadioDtl) obj).setShowOnView(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}
						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_RADIOBUTTON_DTL))
						if (obj.getClass().getName().equalsIgnoreCase(CLASS_CHECKBOX_DTL))
						{
							System.out.println("inside check text");
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("label"))
							{
								((CheckBoxDtl) obj).setLabel(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("data"))
							{
								if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail
										.get(6)).equalsIgnoreCase("option"))
								{
									((CheckBoxDtl) obj).getAlOptionText().add(_textNode.getNodeValue());
								}
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("default"))
							{
								((CheckBoxDtl) obj).setDefaultValue(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("readonly"))
							{
								((CheckBoxDtl) obj).setReadOnly(_textNode.getNodeValue());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-modify-page"))
							{
								((CheckBoxDtl) obj).setShowOnModify(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}
							if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("show-on-view-page"))
							{
								((CheckBoxDtl) obj).setShowOnView(_textNode.getNodeValue());
								System.out.println("((TextDtl)obj).setReadOnly" + ((TextDtl) obj).getShowOnModify());
							}

						}//if (obj.getClass().getName().equalsIgnoreCase(CLASS_CHECKBOX_DTL))
					}//if(((String)_nodeHierarchyDetail.get(3)).equalsIgnoreCase("control"))

				}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("controls"))
				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("initialise-query"))
				{
					try
					{
						if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("fields")) if (((String) _nodeHierarchyDetail
								.get(4)).equalsIgnoreCase("field"))
						{
							((MasterModDtl) objMasterDtl).getAlFields().add(_textNode.getNodeValue().trim());
							//System.out.println("objMasterDtl).getAlFields" + ((MasterModDtl) objMasterDtl).getAlFields());
						}
					}
					catch (Exception e)
					{
						System.out.println("Exception " + e);
						e.printStackTrace();
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("condition"))
					{
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("general"))
						{
							((MasterModDtl) objMasterDtl).setGenCondition(_textNode.getNodeValue());
						}
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("hospital-code"))
						{
							((MasterModDtl) objMasterDtl).setHospitalCode(_textNode.getNodeValue());
							/////////insert query
						}
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("dependent")) if (((String) _nodeHierarchyDetail
								.get(5)).equalsIgnoreCase("clause"))
						{
							((MasterModDtl) objMasterDtl).getAlClause().add(_textNode.getNodeValue());
						}

					}

					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail
							.get(4)).equalsIgnoreCase("table"))
					{
						((MasterModDtl) objMasterDtl).getAlTable().add(_textNode.getNodeValue());
					}
				}

				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("update-query"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("fields"))
					{
						((MasterModDtl) objMasterDtl).getAlFieldsUQ().add(_textNode.getNodeValue());
						//System.out.println("((MasterModDtl)objMasterDtl).getAlFields() ((MasterModDtl) objMasterDtl).getAlFields());
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("table"))
					{
						((MasterModDtl) objMasterDtl).setTableUQ(_textNode.getNodeValue());
						//System.out.println("((MasterModDtl)objMasterDtl).setTable() ((MasterModDtl) objMasterDtl).getTableUQ());
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("condition"))
					{
						((MasterModDtl) objMasterDtl).getConditionUQ().add(_textNode.getNodeValue().trim());
						//System.out.println("((MasterModDtl)objMasterDtl).setTable()"+((MasterModDtl)objMasterDtl).getCondition());				  				
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("hospital-code"))
					{
						((MasterModDtl) objMasterDtl).setHospitalCodeUQ(_textNode.getNodeValue().trim());
						//update querySystem.out.println("((MasterModDtl)objMasterDtl).setTable()"+((MasterModDtl)objMasterDtl).getCondition());				  				
					}
				}if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("redundent-check"))
				{
					
						((MasterModDtl) objMasterDtl).setRedundentQuery(_textNode.getNodeValue().trim());
						//update querySystem.out.println("((MasterModDtl)objMasterDtl).setTable()"+((MasterModDtl)objMasterDtl).getCondition());				  				
					
				}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("insert-query"))		
			}//if(((String)_nodeHierarchyDetail.get(1)).equalsIgnoreCase("dynamic-page"))		

		}//end of case add page        	 
	}//end of method
}//end of class
