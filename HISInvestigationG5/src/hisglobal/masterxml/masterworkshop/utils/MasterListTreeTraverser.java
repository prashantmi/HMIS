package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.masterxml.masterworkshop.tools.ComboDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterListDtl;

import org.w3c.dom.*;
import java.util.*;

public class MasterListTreeTraverser extends MasterTreeTraverser
{
	public MasterListTreeTraverser(Node _rootOfTree, MasterDtl _objMasterDtl)
	{
		super.rootOfTree = _rootOfTree;

		super.objMasterDtl = _objMasterDtl;
		//System.out.println("object:::" + super.objMasterDtl);
	}//end of constructor

	public void processor(Stack _nodeHierarchyDetail, NamedNodeMap _nodeMap)
	{

		int x;
		//System.out.println("inside  attr proceessor");
		//System.out.println("STack::::ggj" + _nodeHierarchyDetail);
		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("common-values")) if (((String) _nodeHierarchyDetail
				.get(1)).equalsIgnoreCase("master-name"))
		{
			//System.out.println("inside common-values::::::: atr");
			Node nd = _nodeMap.item(0);
			String nodename = nd.getNodeName();
			if (nodename.equalsIgnoreCase("hasSequence"))
			{
				objMasterDtl.setHasSequence(nd.getNodeValue());
			}
			if (nodename.equalsIgnoreCase("hasrostersequence"))
			{
				objMasterDtl.setHasRosterSequence(nd.getNodeValue());
			}
		}

		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("list-page"))
		{
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("controls")) if (((String) _nodeHierarchyDetail
					.get(2)).equalsIgnoreCase("control"))
			{
				if (_nodeHierarchyDetail.size() == 3)
				{
					for (int i = 0; i < _nodeMap.getLength(); i++)
					{

						//System.out.println("inside forsss" + i);
						Node nd = _nodeMap.item(i);
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
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								alControls.add(objComboDtl);
								if (i == 0) x = 1;
								else x = 0;
								Node ndee = _nodeMap.item(x);
								objComboDtl.setIndex(ndee.getNodeValue());
								//System.out.println("objComboDtl.setIndex" + objComboDtl.getIndex());
							}
						}
					}

				}//_nodeHierarchyDetail.size()==3)
				else
				{
					//System.out.println("inside else");
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("data"))
					{
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("dynamic")) if (((String) _nodeHierarchyDetail
								.get(5)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(6))
								.equalsIgnoreCase("table"))
						{
							//System.out.println("in table");
							Node nde = _nodeMap.item(0);

							ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
							ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
							//System.out.println("objComboDtl:::::" + objComboDtl);
							//System.out.println("alTableAlias" + objComboDtl.getAlTableAlias());
							//System.out.println("objComboDtl.getClass().getName()" + objComboDtl.getClass().getName());
							if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
							{
								//System.out.println("inside ifffffffffffffff");
								objComboDtl.getAlTableAlias().add(nde.getNodeValue());
								//System.out.println("objCombolDtl.text field" + objComboDtl.getAlTableAlias());
							}
						}//if(((String)_nodeHierarchyDetail.get(6)).equalsIgnoreCase("table")) 							  
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("static")) if (((String) _nodeHierarchyDetail
								.get(5)).equalsIgnoreCase("option"))
						{
							//System.out.println("inside case option");
							Node nder = _nodeMap.item(0);
							ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
							ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
							//System.out.println("objComboDtl:::::" + objComboDtl);
							if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
							{
								objComboDtl.getAlOptionValues().add(nder.getNodeValue());
								//System.out.println("objCombolDtl.text field" + objComboDtl.getAlOptionValues());
							}
						}//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("option"))						     

					}//if(((String)_nodeHierarchyDetail.get(3)).equalsIgnoreCase("data"))
				}//end of else						
			}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("control"))

			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("query-detail")) if (((String) _nodeHierarchyDetail
					.get(2)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail.get(3))
					.equalsIgnoreCase("table"))
			{
				Node ndere = _nodeMap.item(0);
				//System.out.println("inside if of table attr");
				((MasterListDtl) objMasterDtl).getAlTableAlias().add(ndere.getNodeValue());
				//System.out.println("((MasterListDtl)objMasterDtl).getAlTablealias()"+ ((MasterListDtl) objMasterDtl).getAlTableAlias());

			}

		}//end of if case list page

	}//processor(Stack _nodeHierarchyDetail, NamedNodeMap  _nodeMap)

	public void processor(Stack _nodeHierarchyDetail, Node _textNode)
	{

		//System.out.println("text node " + _textNode.getNodeValue());
		//System.out.println("satck vaues in process of if" + _nodeHierarchyDetail);
		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("common-values"))
		{
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("master-name"))
			{
				//System.out.println("case master name");
				//System.out.println("mastertitle "+objMasterDtl);		 
				super.objMasterDtl.setMasterName(_textNode.getNodeValue());
				//System.out.println("dfd");
				//System.out.println("super.objMasterDtl.masterTitle" + super.objMasterDtl.getMasterName());
			}//	if(((String)_nodeHierarchyDetail.get(1)).equalsIgnoreCase("master-title"))

			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("master-title"))
			{
				//System.out.println("case master title");
				//System.out.println("mastertitle " + objMasterDtl);
				super.objMasterDtl.setMasterTitle(_textNode.getNodeValue());
				//System.out.println("dfd");
				//System.out.println("super.objMasterDtl.masterTitle" + super.objMasterDtl.getMasterTitle());
			}//	if(((String)_nodeHierarchyDetail.get(1)).equalsIgnoreCase("master-title"))

		}//end of if(((String)_nodeHierarchyDetail.get(0)).equalsIgnoreCase("common-values"))
		if (((String) _nodeHierarchyDetail.get(0)).equalsIgnoreCase("list-page"))
		{
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("tableHeading"))
			{
				//System.out.println("inside if of table heading ");
				((MasterListDtl) objMasterDtl).setTblHeading(_textNode.getNodeValue());
				//System.out.println("((MasterListDtl)objMasterDtl).tblHeading"	+ ((MasterListDtl) objMasterDtl).getTblHeading());
			}
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("list-table"))
			{

				((MasterListDtl) objMasterDtl).setListTable(_textNode.getNodeValue());
				//System.out.println("((MasterListDtl)objMasterDtl).tblHeading"+((MasterListDtl)objMasterDtl).getTblHeading());
			}

			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("controls")) if (((String) _nodeHierarchyDetail
					.get(2)).equalsIgnoreCase("control"))
			{
				if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("default"))
				{
					//System.out.println("inside if of case default");
					ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
					ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
					//System.out.println("class name" + objComboDtl.getClass().getName());
					if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
					{
						//System.out.println("inside default");
						//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
						objComboDtl.setDefaultValue(_textNode.getNodeValue());
						//System.out.println("objCombolDtl.text field" + objComboDtl.getDefaultValue());
					}
				}//end of if(_nodeHierarchyDetail.get(3)=="label")
				if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("selection-mandatory"))
				{
					//System.out.println("inside if of case selection-mandatory");
					ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
					ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
					//System.out.println("class name" + objComboDtl.getClass().getName());
					if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
					{
						//System.out.println("inside default");
						//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
						objComboDtl.setSelectionMandatory(_textNode.getNodeValue());
						//System.out.println("objCombolDtl.text field" + objComboDtl.getSelectionMandatory());
					}
				}

				if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("label"))
				{
					//System.out.println("inside case label");

					ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
					ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
					//System.out.println("class name::::::::::" + objComboDtl.getClass().getName());
					if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
					{

						objComboDtl.setLabel(_textNode.getNodeValue());
						//System.out.println("objComboDtl.setLabel" + objComboDtl.getLabel());
					}
				}//end of if(_nodeHierarchyDetail.get(3)=="label")*/

				if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("data"))
				{
					//System.out.println("in side data");
					if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("dynamic"))
					{

						if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("tables")) if (((String) _nodeHierarchyDetail
								.get(6)).equalsIgnoreCase("table"))
						{
							//System.out.println("inside if of text table");
							ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
							ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
							//System.out.println("class name" + objComboDtl.getClass().getName());

							if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
							{
								//System.out.println("inside");
								//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
								objComboDtl.getAlTable().add(_textNode.getNodeValue());
								//System.out.println("objCombolDtl.text field" + objComboDtl.getAlTable());
							}
						}

						if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("options"))
						{
							if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("text-field"))
							{
								//System.out.println("inside if of text field");
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
								//System.out.println("class name" + objComboDtl.getClass().getName());

								if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
								{
									//System.out.println("inside");
									//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
									objComboDtl.setTextField(_textNode.getNodeValue());
									//System.out.println("objCombolDtl.text field" + objComboDtl.getTextField());
								}
							}

							if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("value-field"))
							{

								//System.out.println("inside if of value field");
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
								//System.out.println("class name" + objComboDtl.getClass().getName());

								if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
								{
									//System.out.println("inside");
									//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
									objComboDtl.setValueField(_textNode.getNodeValue());
									//System.out.println("objCombolDtl.text field" + objComboDtl.getValueField());
								}
							}

						}///if(((String)_nodeHierarchyDetail.get(5)).equalsIgnoreCase("options"))
						if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("condition"))
						{
							if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("general"))
							{
								//System.out.println("inside if of condition");
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
								//System.out.println("class name" + objComboDtl.getClass().getName());

								if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
								{
									//System.out.println("inside");
									//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
									objComboDtl.setGenCondition(_textNode.getNodeValue());
									//System.out.println("objCombolDtl.text field" + objComboDtl.getGenCondition());
								}

							}//if(((String)_nodeHierarchyDetail.get(6)).equalsIgnoreCase("general"))

							if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("hospital-code"))
							{
								//System.out.println("inside if of condition");
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
								//System.out.println("class name" + objComboDtl.getClass().getName());

								if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
								{
									//System.out.println("inside");
									//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
									objComboDtl.setHospitalCode(_textNode.getNodeValue());
									//System.out.println("objCombolDtl.text field" + objComboDtl.getHospitalCode());
								}

							}

							if (((String) _nodeHierarchyDetail.get(6)).equalsIgnoreCase("dependent")) if (((String) _nodeHierarchyDetail
									.get(7)).equalsIgnoreCase("clause"))
							{
								//System.out.println("inside if of condition");
								ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
								ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
								//System.out.println("class name" + objComboDtl.getClass().getName());

								if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
								{
									//System.out.println("inside");
									//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
									objComboDtl.getAlDepCondition().add(_textNode.getNodeValue());
									//System.out.println("objCombolDtl.text field" + objComboDtl.getAlDepCondition());
								}
							}//if  (((String)_nodeHierarchyDetail.get(6)).equalsIgnoreCase("dependent"))							

						}//end of if for case condition					   
					}//end of if for case dynamic	
					//System.out.println("before static");
					//System.out.println("val at index 4" + (String) _nodeHierarchyDetail.get(4));
					//System.out.println("val at index 5" + (String) _nodeHierarchyDetail.get(5));
					//start for case static 
					//if(((String)_nodeHierarchyDetail.get(4)).equalsIgnoreCase("static"))
					if (((String) _nodeHierarchyDetail.get(5)).equalsIgnoreCase("option"))
					{
						//System.out.println("inside if of case static");
						ArrayList alControls = ((MasterListDtl) objMasterDtl).getAlControls();
						ComboDtl objComboDtl = (ComboDtl) alControls.get(alControls.size() - 1);
						//System.out.println("class name" + objComboDtl.getClass().getName());

						if (objComboDtl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
						{
							//System.out.println("inside");
							//System.out.println("class name::::::::::"+objControlDtl.getClass().getName());		 
							objComboDtl.getAlOptiontext().add(_textNode.getNodeValue());
							//System.out.println("objCombolDtl.text field" + objComboDtl.getAlOptiontext());
						}

					}//end of if for case option for static
				}//end of if for case data							  
			}//end of if for case control
			//at this stage whole subree for control is traversed 
			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("columns"))
			{
				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("column"))
				{
					//System.out.println("inside if of column for columns after control ");
					((MasterListDtl) objMasterDtl).getAlColumnHeading().add(_textNode.getNodeValue());
					//System.out.println("((MasterListDtl)objMasterDtl).tblHeading"	+ ((MasterListDtl) objMasterDtl).getAlColumnHeading());
				}//end ofif(((String)_nodeHierarchyDetail.get(1)).equalsIgnoreCase("column"))

			}//end of if for case column

			if (((String) _nodeHierarchyDetail.get(1)).equalsIgnoreCase("query-detail"))
			{
				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("fields"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("field"))
					{
						//System.out.println("inside if of field ");
						((MasterListDtl) objMasterDtl).getAlFields().add(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).field"+ ((MasterListDtl) objMasterDtl).getAlFields());

					}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("fields"))

				}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("fields"))

				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("primaryKey"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("field"))
					{
						//System.out.println("inside if of field for primary key");
						((MasterListDtl) objMasterDtl).getAlPrimarykey().add(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).primary key"+ ((MasterListDtl) objMasterDtl).getAlPrimarykey());
					}//if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("fields"))for primary key

				}//	if(((String)_nodeHierarchyDetail.get(2)).equalsIgnoreCase("primaryKey"))

				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("tables"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("table"))
					{
						//System.out.println("inside if of table query");
						((MasterListDtl) objMasterDtl).getAlTable().add(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).getAlTable()"	+ ((MasterListDtl) objMasterDtl).getAlTable());

					}//if(((String)_nodeHierarchyDetail.get(3)).equalsIgnoreCase("table"))
				}

				if (((String) _nodeHierarchyDetail.get(2)).equalsIgnoreCase("condition"))
				{
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("general"))
					{
						//System.out.println("inside if of query condition");
						((MasterListDtl) objMasterDtl).setGencondition(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).condition"+ ((MasterListDtl) objMasterDtl).getGencondition());

					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("general-inactive"))
					{
						//System.out.println("inside if of query condition");
						((MasterListDtl) objMasterDtl).setGenConditionInactive(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).condition"+ ((MasterListDtl) objMasterDtl).getGenConditionInactive());
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("dependent")) 
						{
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("clause"))
							{
							//System.out.println("inside if of clause");
							((MasterListDtl) objMasterDtl).getAlClause().add(_textNode.getNodeValue());
							//System.out.println("((MasterListDtl)objMasterDtl).clause"	+ ((MasterListDtl) objMasterDtl).getAlClause());
							}
						if (((String) _nodeHierarchyDetail.get(4)).equalsIgnoreCase("clause-alphanumeric"))
							{
						//System.out.println("inside if of clause");
						((MasterListDtl) objMasterDtl).getAlAlphanumericClause().add(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).clause"	+ ((MasterListDtl) objMasterDtl).getAlAlphanumericClause());
							}
						}

					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("isvalid-field"))
					{
						//System.out.println("inside if of query condition isvalid-field");
						((MasterListDtl) objMasterDtl).setIsvalidfield(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).condition"+ ((MasterListDtl) objMasterDtl).getIsvalidfield());
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("hospital-code"))
					{
						//System.out.println("inside if of query condition hospital-code");
						((MasterListDtl) objMasterDtl).setHospitalCode(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).condition"	+ ((MasterListDtl) objMasterDtl).getIsvalidfield());
					}
					if (((String) _nodeHierarchyDetail.get(3)).equalsIgnoreCase("orderBy"))
					{
						//System.out.println("inside if of query condition isvalid-field");
						((MasterListDtl) objMasterDtl).setOrderby(_textNode.getNodeValue());
						//System.out.println("((MasterListDtl)objMasterDtl).condition"+ ((MasterListDtl) objMasterDtl).getOrderby());
					}

				}
			}//end of if for case query detail
		}//end of if case list page
	}//end of method
}//end of class

