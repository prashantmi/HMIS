package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.masterxml.masterworkshop.dao.MasterBuilderDb;
import hisglobal.masterxml.masterworkshop.tools.Combo;
import hisglobal.masterxml.masterworkshop.tools.ComboDtl;
import hisglobal.masterxml.masterworkshop.tools.Control;
import hisglobal.masterxml.masterworkshop.tools.MasterDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterListDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterListTO;
import hisglobal.masterxml.masterworkshop.tools.MasterTO;
import hisglobal.masterxml.masterworkshop.dao.*;
import hisglobal.persistence.JDBCTransactionContext;

import java.io.InputStream;
import java.util.*;
import org.w3c.dom.*;

public class MasterBuilder
{
	public static final String LIST_PAGE = "list";
	public static final String ADD_PAGE = "add";
	public static final String MODIFY_PAGE = "modify";
	public static final String REPORT_PAGE = "report";
	public static final String VIEW_PAGE = "view";
	public static final String MASTER_LIST_DTL = "hisglobal.masterxml.masterworkshop.tools.MasterListDtl";
	public static final String COMBO_DTL = "hisglobal.masterxml.masterworkshop.tools.ComboDtl";
	//private	String xmlFile="maritalstatus.xml";	
	private Document docMasterConfig;
	private static MasterBuilder objMasterBuilder;
	public static Map domMap = new HashMap();

	private MasterBuilder(InputStream _is)
	{
		//System.out.println("creating instance first time");
		docMasterConfig = XmlParser.getDomTree(_is);
		removeEmptyTextNode(docMasterConfig);
	}

	public static MasterBuilder getInstance(InputStream _is)
	{

		objMasterBuilder = new MasterBuilder(_is);
		return objMasterBuilder;

	}

	//this method is called for first request to add page
	public MasterTO buildMaster(String _masterName, String _masterType, MasterTO _objLstMstTo)
	{
		MasterTO objMasterTO = null;
		if (_masterType.equalsIgnoreCase("add"))
		{
			MasterAddBuilder objMasterAddBuilder = new MasterAddBuilder(docMasterConfig, _masterName);
			objMasterTO = objMasterAddBuilder.buildMaster(_objLstMstTo);
		}
		return objMasterTO;
	}

	//this method is called for subsequent request to add page
	public MasterTO buildMaster(MasterTO _objMstAddTo, hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		System.out.println("inside buildMaster for add subsequent");
		MasterAddBuilder objMasterAddBuilder = new MasterAddBuilder();
		_objMstAddTo = objMasterAddBuilder.reBuildAdd(_objMstAddTo, _formBeanAdd);
		return _objMstAddTo;
	}

	//for add save 
	public String saveRecord(MasterTO _objMstAddTo, MasterTO _objLstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerAddFormBn _formBeanAdd)
	{
		String recordSaved;
		try{
		System.out.println("inside SAVERECORD");
		
		MasterAddBuilder objMasterAddBuilder = new MasterAddBuilder();
		recordSaved = objMasterAddBuilder.save(_objMstAddTo, _objLstTo, _formBeanAdd);
		}catch(HisDataAccessException e)
		{
			throw new HisDataAccessException();
		}
		finally{}
		return recordSaved;
	}

	//for modify
	public String updateRecord(MasterTO _objMstModTo, MasterTO _objLstTo,
			hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod)
	{

		System.out.println("inside updateRECORD");
		String recordupdated;
		MasterModifyBuilder objMasterModBuilder = new MasterModifyBuilder();
		recordupdated = objMasterModBuilder.update(_objMstModTo, _objLstTo, _formBeanMod);
		return recordupdated;
	}

	//this build master is called for the first time when the list page is to be formed
	public MasterTO buildMaster(String _masterFileName, String _hospitalCode)
	{
		MasterTO objMasterTO = null;
		//System.out.println("building master first time");
		Node masterNode = extractSpecMaster(_masterFileName);

		//System.out.println("node::" + masterNode);
		if (masterNode == null)
		{
			//System.out.println("Masterfile name & master node didn't match");
			return objMasterTO;
		}
		else
		{

			//System.out.println("master name found");
			MasterDtl objMasterDtl = null;
			objMasterDtl = this.getMasterDtl(masterNode);
			((MasterListDtl) objMasterDtl).display();
			//setting values for transfer object        		
			objMasterTO = this.getMasterTO(objMasterDtl, _hospitalCode);
			objMasterTO.setMasterName(_masterFileName);
			//System.out.println("objMasterDtl.getHasSequence()" + objMasterDtl.getHasSequence());
			objMasterTO.setHasSequence(objMasterDtl.getHasSequence());
			objMasterTO.setHasRosterSequence(objMasterDtl.getHasRosterSequence());
		}
		return objMasterTO;
	}

	/*public MasterTO buildMaster(String _masterName, String _masterType,MasterTO _objMstTO,masterWorkshop.MstHandlerAddFormBn _formBean)
	{
										
	   	if(_masterType.equalsIgnoreCase("add")){
			MasterAddBuilder objMasterAddBuilder = new MasterAddBuilder(docMasterConfig, _masterName);
			objMasterTO = objMasterAddBuilder.buildMaster(_objMstTO,_formBean);
			
		}
		return _objMstTO;
		
	}*/
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
				+ strTable + " where " + _objComboDtl.getGenCondition();

		/////Adding where clause for hospitalCode///////////

		comboQuery = setHospitalCode(comboQuery, _objComboDtl, hospitalCode);
		return comboQuery;
	}

	/**
	 * This method first creates the instance of MasterModifyBuilder and pass to it the document node of the XML to be processed
	 * 
	 * @param _masterName >>>master name for which the modifiaction page is to be build.
	 * @param _chk        >>>Primary key value for which the records are to be retrieved. 
	 * @param _activeStatus >>>weather record to be modified is active/inactive.
	 * @return
	 */
	//this method is called for first request to Modify Page 
	public MasterTO buildMaster(String _masterName, String _chk, String _activeStatus, MasterTO _objMstTo)
	{
		MasterTO objMasterTO = null;
		MasterModifyBuilder objMasterModifyBuilder = new MasterModifyBuilder(docMasterConfig, _masterName);
		objMasterTO = objMasterModifyBuilder.buildMaster(_chk, _activeStatus, _objMstTo);

		return objMasterTO;
	}

	//this method is called for subsequent request to modify page	 
	public MasterTO buildMaster(MasterTO _objMstModTo, hisglobal.masterxml.masterworkshop.MstHandlerModifyFormBn _formBeanMod)
	{
		//System.out.println("inside buildMaster for MOD subsequent");
		MasterModifyBuilder objMasterModifyBuilder = new MasterModifyBuilder();
		_objMstModTo = objMasterModifyBuilder.reBuildMod(_objMstModTo, _formBeanMod);
		return _objMstModTo;
	}

	public MasterTO buildMaster(MasterTO _objMstTO, hisglobal.masterxml.masterworkshop.MstHandlerListFormBn _formBean,
			String _hospitalCode)
	{
		//System.out.println("inside buildMaster MasterTO _objMstTO,hisglobal.masterxml.masterworkshop.MstHandlerListFormBn _formBean");
		try
		{
			//MasterTO objMasterTO=null;
			boolean changeInParent = false;
			String strComboQuery = "";
			String state = _formBean.getIsActive();
			//System.out.println("state::" + state);
			String[] controls = _formBean.getControls();
		//	System.out.println("controls:::" + controls);
			//System.out.println("inside build master");
			String hospitalCode = _objMstTO.getHospitalCode();
			int parent;

			for (int i = 0; i < controls.length; i++)
			{
				//System.out.println("controls " + i + ":" + controls[i]);
			}

			ArrayList alcontrols = _objMstTO.getControls();
			//System.out.println("alcontrols.size()" + alcontrols.size());
			MasterListDtl objLstDtl = ((MasterListTO) _objMstTO).getObjMstLstDtl();

			//  for(int i=0;i<alcontrols.size();i++)	<<<<<<<<<<
			//System.out.println("controls.length" + controls.length);
			for (int i = 0; i < alcontrols.size(); i++)
			//for(int i=0;i<controls.length;i++)
			{
				//System.out.println("inside for");
				//get the present conntrol to work on from TO 
				Control objControl = (Control) alcontrols.get(i);

				ArrayList alcontrolsmst = objLstDtl.getAlControls();
				ComboDtl objComboDtl = (ComboDtl) alcontrolsmst.get(i);
				//String  depCondition= objComboDtl.getDepCondition();				 
				//System.out.println("depCondition"+depCondition);
				//System.out.println("");

				if (objComboDtl.getAlDepCondition().size() > 0)
				{
					//System.out.println("inside combos aldepcondition");
					strComboQuery = buildComboQuery(objComboDtl, hospitalCode);//form combo query w/o any gen condition
					//System.out.println("strComboQuery" + strComboQuery);
					for (int k = 0; k < objComboDtl.getAlDepCondition().size(); k++)
					{
						String depCondition = (String) objComboDtl.getAlDepCondition().get(k);
						int idx = depCondition.lastIndexOf("#");

						//System.out.println("inside if case combo dependency on same page");
						parent = getParent(depCondition);

						//compare present parents value(FB) with the previous value(TO)
						Control objCtrl = (Control) alcontrols.get(parent);
						String val = objCtrl.getDefaultValue().trim();
						//System.out.println("val:::in combo" + val);
						//System.out.println("controls[parent]" + controls[parent].trim());
						if (controls[parent].equals("-1") || !val.equals(controls[parent])) // If value set to "-1" rest should be cleared
						{
							//System.out.println("" + controls[parent]);
							changeInParent = true;
							//strComboQuery=strComboQuery+" "+newDependCond+controls[parent];                             				
							StringBuffer sb = new StringBuffer(depCondition);
							int start = sb.lastIndexOf("#");
							StringBuffer sbe = sb.replace(start, start + 2, (String) controls[parent]);
							strComboQuery = strComboQuery + " " + sbe.toString();
							//System.out.println("parent: "+parent+" for combo: "+i+" has changed");				   			
							System.out.println("ComboQuery::" + strComboQuery);
							//System.out.println(" has dependence change Combo:" + i);
							controls[i] = "-1";
						}
					}
				}
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

			}//end of for			 

			//logic for form list query	 			

			//System.out.println("form list query");
			ArrayList alclause = objLstDtl.getAlClause();
			ArrayList alAlphanumericClause = objLstDtl.getAlAlphanumericClause();
			String listQuery = "";
			String querywithoutCondition = buildListQuery(objLstDtl);
			String query = appendGeneralWhereCondition(querywithoutCondition, objLstDtl, state);

			
			/////ArrayList for numeric clause
			for (int i = 0; i < alclause.size(); i++)
			{
				//int x=Integer.parseInt("-1");
				if (controls.length > i && !controls[i].equals("-1"))
				{
					//System.out.println("in side for alclause");
					String clause = (String) alclause.get(i);
					int idx = clause.lastIndexOf("#");
					parent = getParent(clause);
					//System.out.println("parent:::" + parent);
					Control objControl = (Control) alcontrols.get(parent);
					//System.out.println("sdhfhdhjhddjddddddddddd");
					//add that dependent condition to the list query
					query = query + clause.substring(0, idx - 1) + String.valueOf(controls[parent]) + " AND ";

				}
			}
		/////ArrayList for alphanumeric clause
			for (int i = 0; i < alAlphanumericClause.size(); i++)
			{
				//int x=Integer.parseInt("-1");
				if (controls.length > i && !controls[i].equals("-1"))
				{
					//System.out.println("in side for alclause");
					String clause = (String) alAlphanumericClause.get(i);
					int idx = clause.lastIndexOf("#");
					parent = getParent(clause);
					//System.out.println("parent:::" + parent);
					Control objControl = (Control) alcontrols.get(parent);
					//System.out.println("sdhfhdhjhddjddddddddddd");
					//add that dependent condition to the list query
					query = query + clause.substring(0, idx - 1) +"'" +String.valueOf(controls[parent]) +"'"+ " AND ";

				}
			}

			/*System.out.println("in side for alclause"); 
			String clause=(String)alclause.get(i);
			int idx=clause.lastIndexOf("#");				
			int parent =getParent(clause);				
			System.out.println("parent:::"+parent);				
			Control objControl=(Control)alcontrols.get(parent);
			System.out.println("sdhfhdhjhddjddddddddddd");
						 
			if(!(objControl.getDefaultValue().equals(controls[parent])))
			  {
				//objControl.setDefaultValue(controls[parent]);	
			    listQuery= buildListQuery(objLstDtl);
			    listQuery=listQuery+clause.substring(0,idx-1)+String.valueOf(controls[parent]);				    																																																						
			  }	
			 */

			if (!objLstDtl.getIsvalidfield().equalsIgnoreCase("false")) query = appendState(query, state, objLstDtl,
					_hospitalCode);

			query = setHospitalCode(query, objLstDtl, _hospitalCode);
			query = setOrderBy(query, objLstDtl);

			// query=query+objLstDtl.getOrderby();
			//Query=setOrderBy(listQuery,objLstDtl);
			//System.out.println("Query after orderBy" + query);
			ArrayList alDatalist = new ArrayList();
			alDatalist = fetchRecord(query);
			if (isCompositePk(objLstDtl))
			{
				ArrayList alData = new ArrayList();
				alData = getAlDataForCPk(alDatalist, objLstDtl);
				((MasterListTO) _objMstTO).setAlData(alData);
			}
			else
			{
				((MasterListTO) _objMstTO).setAlData(alDatalist);
			}

			//System.out.println("alcontrols.size()" + alcontrols.size());
			for (int i = 0; i < alcontrols.size(); i++)
			{
				if (controls.length > i /*&& !controls[i].equals("-1")*/)
				{
					//System.out.println("inside for");
					Control objControl = (Control) alcontrols.get(i);
					//System.out.println("objControl.getDefaultValue():::" + objControl.getDefaultValue());
					//System.out.println("control::" + i + "::" + controls[i]);
					/*			  if(!(objControl. ().equals(controls[i])))
					 {*/
					objControl.setDefaultValue(controls[i]);
					//System.out.println("objControl.getDefaultValue()in if" + objControl.getDefaultValue());
					/*				 }//
					 */
					//System.out.println("objControl.getDefaultValue()::: after" + objControl.getDefaultValue());
				}//			   
			}
			((MasterListTO) _objMstTO).setIsActive(_formBean.getIsActive());
			return _objMstTO;
		}
		catch (Exception e)
		{
			System.out.println("Exception in build master of build" + e);
			e.printStackTrace();

		}
		return _objMstTO;

	}// end of method

	public int getParent(String _depCondition)
	{

		int idx = _depCondition.lastIndexOf("#");
		//System.out.println("idx" + idx);
		String parent = _depCondition.substring(idx + 1, idx + 2);
		//System.out.println("parent:::" + parent);
		int parentI = Integer.parseInt(parent.trim());
		return parentI;
	}

	public String appendState(String _listQuery, String state, MasterListDtl _objMasterLstDtl, String _hospitalCode)
	{

		if (state.equalsIgnoreCase("") || state == null)
		//state=MasterConfig.ACTIVE;
		state = "1";
		//System.out.println("in append state");
		_listQuery = _listQuery + " " + _objMasterLstDtl.getIsvalidfield() + "=" + state;

		/////Adding where clause for hospitalCode///////////
		// String whereClauseHospitalCode=" AND A.gnum_hospital_code = "+_hospitalCode;
		// _listQuery=_listQuery+whereClauseHospitalCode;

		//System.out.println("_listQuery afetr adding where clause for hospital code:::" + _listQuery);

		return _listQuery;
	}

	boolean isCompositePk(MasterListDtl objMasterListDtl)
	{
		if (objMasterListDtl.getAlPrimarykey().size() > 1) return true;
		return false;
	}

	public ArrayList getAlDataForCPk(ArrayList _aldatalist, MasterListDtl _objMasterListDtl)
	{
		ArrayList aldata = new ArrayList();
		ArrayList pKList = _objMasterListDtl.getAlPrimarykey();
		//System.out.println("inside getAlDataForCPk:::::::");
		//System.out.println("_aldatalist SIZE" + _aldatalist.size());
		//System.out.println("_objMasterListDtl.getAlFields().size()" + _objMasterListDtl.getAlFields().size());
		String pkData = "";
		for (int i = 0; i < _aldatalist.size();)
		{
			String newPkData = (String) _aldatalist.get(i++);
			for (int j = 0; j < pKList.size() - 1; j++)
			{
				newPkData = newPkData + "^" + (String) _aldatalist.get(i++);
				//System.out.println("pkData" + newPkData);
				//System.out.println("I:::::" + i);
			}
			aldata.add(newPkData);
			//System.out.println("newPkData" + newPkData);

			for (int k = 0; k < _objMasterListDtl.getAlFields().size(); k++)
			{
				//System.out.println("I:::::" + i);
				aldata.add((String) _aldatalist.get(i++));
			}
			//System.out.println("aldata at the end of onr iteration" + aldata);
			//i=i+_objMasterListDtl.getAlFields().size();
		}
		//System.out.println("aldata fo cpk" + aldata);
		return aldata;
	}

	/**
	 * This method returns Master List TO Object with combo queries and list queries
	 * Also calls the append state method to signify the record validity
	 * Also fetch records on the basis of these queries
	 * if Composite primarykeys are found it calls the getAlDataForCPk which returns the array list of cpk
	 * with ^ as sepraor      
	 * @see #buildComboQuery(MasterListDtl)
	 * @see #buildListQuery(MasterListDtl)
	 * @see #appendState(String, String, MasterListDtl)
	 * @see #getAlDataForCPk(ArrayList, MasterListDtl)
	 * @param _objMasterDtl MasterDtl object
	 */

	private String setOrderBy(String _query, MasterListDtl _objMasterListDtl)
	{
		//System.out.println("objMasterListDtl.getOrderby()" + _objMasterListDtl.getOrderby());
		//System.out.println("query before orderBy" + _query);
		if (_objMasterListDtl.getOrderby() == null)
		{
			_objMasterListDtl.setOrderby("");
		}
		if (_objMasterListDtl.getOrderby() != null)
		{
			_query = _query + _objMasterListDtl.getOrderby();
		}
		return _query;
	}

	private String setHospitalCode(String _query, MasterListDtl _objMasterListDtl, String _hospitalCode)
	{

		if (_objMasterListDtl.getHospitalCode() != null)
		{
			_query = _query + " AND " + _objMasterListDtl.getHospitalCode() + "=" + _hospitalCode;
		}
		return _query;
	}

	private String setHospitalCode(String _query, ComboDtl _objComboDtl, String _hospitalCode)
	{

		if (_objComboDtl.getHospitalCode() != null)
		{
			_query = _query + " " + _objComboDtl.getHospitalCode() + "=" + _hospitalCode;
		}
		return _query;
	}

	private String appendGeneralWhereCondition(String _query, MasterListDtl _objMasterListDtl, String _state)
	{
		//String query="";
		if (_state.equalsIgnoreCase("1"))
		{
			_query = _query + " where";
			//_query=_query+
			String que = (_objMasterListDtl.getGencondition() == null ? "" : _objMasterListDtl.getGencondition());
			_query = _query + que;
			//System.out.println("_query" + _query);
		}

		else
		{
			_query = _query + " where";
			//_query=_query+
			String que = (_objMasterListDtl.getGenConditionInactive() == null ? "" : _objMasterListDtl
					.getGenConditionInactive());
			_query = _query + que;
			//System.out.println("_query" + _query);

		}

		return _query;

	}

	private MasterTO getMasterTO(MasterDtl _objMasterDtl, String _hospitalCode)
	{
		//System.out.println("inside getMasterTO");
		MasterTO objMasterTO = null;
		ArrayList aldatalist = null;
		ArrayList aldata = null;
		String state = "1";
		ArrayList alObjCombo = new ArrayList();
		try
		{
			if (_objMasterDtl.getClass().equals(Class.forName(this.MASTER_LIST_DTL)))
			{
				//System.out.println("inside if ggggggg");
				MasterListDtl objMasterListDtl = (MasterListDtl) _objMasterDtl;

				if (objMasterListDtl.getAlControls().size() > 0)
				{
					alObjCombo = buildComboQuery(objMasterListDtl, _hospitalCode);
				}
				String querywithoutCondition = buildListQuery(objMasterListDtl);
				String query = appendGeneralWhereCondition(querywithoutCondition, objMasterListDtl, state);
				if (!objMasterListDtl.getIsvalidfield().equalsIgnoreCase("false")) query = appendState(query, state,
						objMasterListDtl, _hospitalCode);

				////////adding hospital code clause//////////////////////////////
				query = setHospitalCode(query, objMasterListDtl, _hospitalCode);

				query = setOrderBy(query, objMasterListDtl);

				aldatalist = fetchRecord(query);
				if (isCompositePk(objMasterListDtl))
				{
					ArrayList alData = getAlDataForCPk(aldatalist, objMasterListDtl);
					objMasterTO = SetTOValues(objMasterListDtl, alObjCombo, alData);
				}
				else
				{
					objMasterTO = SetTOValues(objMasterListDtl, alObjCombo, aldatalist);
				}
				return objMasterTO;
			}
		}//end of try
		catch (Exception e)
		{
			System.out.println("Exception " + e);
		}//end of catch 
		return objMasterTO;
	}//end of getmasterTO

	private Node extractSpecMaster(String _masterName)
	{
		Node ndmaster = null;
		try
		{
			//System.out.println("inside extractSpecMaster");
			Node ndmasterconfig = docMasterConfig.getFirstChild();
			NodeList nl = ndmasterconfig.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++)
			{
				if (nl.item(i).getNodeType() != Node.TEXT_NODE)
				{
					ndmaster = nl.item(i);
					NamedNodeMap mapAttr = ndmaster.getAttributes();
					Node ndattr = mapAttr.getNamedItem("name");
					String attrvalue = ndattr.getNodeValue();
					//System.out.println("attr value" + attrvalue);
					if (attrvalue.equalsIgnoreCase(_masterName))
					{
						//System.out.println("Inside if");
						return ndmaster;
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	public MasterDtl getMasterDtl(Node _masterNode)
	{
		MasterDtl objMasterDtl = null;
		//System.out.println("in get Master dtl");
		MasterListDtlExtractor objlistdtlExtactor = new MasterListDtlExtractor(_masterNode);
		objMasterDtl = objlistdtlExtactor.getMasterDtl();
		return objMasterDtl;
	}

	/*public  void traverse(Node node)
	{
	    printNode(node);			
	    if (node.hasChildNodes())
	    {
	        Node child=node.getFirstChild();
	        while (child!=null)
	        {
	            traverse(child);
	            child=child.getNextSibling();					
	        }//end of while				
	    }//end of if
	}//end of traverse
	
	public void printNode(Node node)
	{             
	     System.out.print("node type is::"+node.getNodeType());
	     System.out.print("node name is::"+node.getNodeName());			
	     System.out.print("node value is::"+node.getNodeValue());
	     System.out.print("node parent is::"+node.getParentNode().getNodeName());
	     System.out.println(" ");			 
	}//end of print
	                 
	 public MasterTO parseDom()
	 {
	    XmlParser  parserObj=new XmlParser();
	    this.doc=parserObj.getDomTree(xmlFile);
	    Node masterNode=extractSpecMaster();
	    System.out.println("adsadbhsa"+masterNode.getNodeName()); 	     	   	   	   
	//	    System.out.println("master Node"+masterNode.getNodeName());
	    removeEmptyTextNode(masterNode);
	    traverse(masterNode);
	    MasterListDtlExtractor objlistdtlExtactor=new MasterListDtlExtractor(masterNode);
	    objMasterDtl=objlistdtlExtactor.getMasterDtl();
	    NamedNodeMap ndmap =masterNode.getAttributes();
	    System.out.println("ndmap"+ndmap);
	    Node nde=ndmap.item(0);
	    System.out.println("attr Node"+nde.getNodeValue());
	    objMasterDtl.setMasterName(nde.getNodeValue());	   
	    ((MasterListDtl)objMasterDtl).display();	    
	    //setting values for transfer object	            
		formListQuery();
	    formComboQuery();		
			 
		return objMasterTO;  	  	   		 		 
	  }//end of method parse*/

	public MasterTO SetTOValues(MasterListDtl _objMasterListDtl, ArrayList _alObjCombo, ArrayList _alDataList)
	{
		//System.out.println("inside SetTOValues");
		MasterTO objMasterTO = new MasterListTO();

		objMasterTO.setTitle(_objMasterListDtl.getMasterTitle());
		for (int i = 0; i < _alObjCombo.size(); i++)
		{
			objMasterTO.getControls().add(_alObjCombo.get(i));
		}

		objMasterTO.setTblHeading(_objMasterListDtl.getMasterName());
		((MasterListTO) objMasterTO).setAlColHeading(_objMasterListDtl.getAlColumnHeading());
		((MasterListTO) objMasterTO).setNoOfColumn(_objMasterListDtl.getAlColumnHeading().size());
		((MasterListTO) objMasterTO).setNoOfPrimaryKey(_objMasterListDtl.getAlPrimarykey().size());
		((MasterListTO) objMasterTO).setAlData(_alDataList);
		((MasterListTO) objMasterTO).setObjMstLstDtl(_objMasterListDtl);
		((MasterListTO) objMasterTO).setIsActive("1");
		((MasterListTO) objMasterTO).display();

		//objMasterTO.setTblHeading(objMasterDtl.getMasterName());
		return objMasterTO;
	}

	public String buildListQuery(MasterListDtl _objMasterListDtl)
	{

		//System.out.println("inside build list query");
		String strPkey = "";
		String strField = "";
		int len = _objMasterListDtl.getAlPrimarykey().size();
		//System.out.println("len::" + len);

		for (int i = 0; i < len; i++)
		{
			strPkey = strPkey + _objMasterListDtl.getAlPrimarykey().get(i);
			if (i != (len - 1))
			{
				strPkey = strPkey + ",";
				//System.out.println("strPkey" + strPkey);
			}
			//System.out.println("strPkey" + strPkey);
		}
		len = _objMasterListDtl.getAlFields().size();
		for (int i = 0; i < len; i++)
		{
			strField = strField + _objMasterListDtl.getAlFields().get(i);
			if (i != (len - 1))
			{
				strField = strField + ",";
			}
			//System.out.println("strField" + strField);
		}
		String strTable = "";
		String strAlias = "";
		len = _objMasterListDtl.getAlTable().size();
		//System.out.println("len of altable" + len);
		for (int i = 0; i < len; i++)
		{
			strTable = strTable + _objMasterListDtl.getAlTable().get(i);
			strTable = strTable + " " + _objMasterListDtl.getAlTableAlias().get(i);
			if (i != (len - 1))
			{
				strTable = strTable + ",";
			}
			//System.out.println("strTable" + strTable);
		}

		String strlistQuery = "select " + strPkey + "," + strField + " from " + strTable + " ";

		//String Query="select "+primarykey+", "+strField+" from "+strTable+" where "+condition;
		System.out.println("LIST Query::" + strlistQuery);
		//ArrayList alDatalist= fetchRecord(strlistQuery);		

		return strlistQuery;
	}//end of form list query*/ 

	public ArrayList buildComboQuery(MasterListDtl _objMasterListDtl, String _hospitalCode)
	{
		//System.out.println("inside buildComboQuery");
		ArrayList alTxtField;
		ArrayList alValField;
		ArrayList alComboObj = new ArrayList();
		ComboDtl objComboDtl;
		boolean readonly = false;
		Combo objCombo = null;
		if (_objMasterListDtl.getAlControls().size() > 0)
		{
			//System.out.println(" inside if of build combo data");
			for (int i = 0; i < _objMasterListDtl.getAlControls().size(); i++)
			{
				//System.out.println(" inside for of build combo datafgfgg");
				Object obj = _objMasterListDtl.getAlControls().get(i);
				try
				{
					if (obj.getClass().equals(Class.forName(this.COMBO_DTL)))
					{
						objComboDtl = (ComboDtl) obj;
						//check weather the combo data to be displayed is dynamic or static
						if (objComboDtl.getAlOptionValues().size() > 0)
						{
							//System.out.println(" inside case static");
							alTxtField = new ArrayList(objComboDtl.getAlOptiontext());
							alValField = new ArrayList(objComboDtl.getAlOptionValues());
						}//end of case static
						else
						{//case for dynamic
							alTxtField = new ArrayList();
							alValField = new ArrayList();
							//System.out.println(" inside case dynamic");
							int len = objComboDtl.getAlTable().size();
							//System.out.println(" SIZE of altableinside case dynamic" + objComboDtl.getAlTable().size());
							String strTable = "";
							// System.out.println("objComboDtl.depCondition"+objComboDtl.getDepCondition());							  
							for (int j = 0; j < len; j++)
							{
								strTable = strTable + objComboDtl.getAlTable().get(j);
								strTable = strTable + " " + objComboDtl.getAlTableAlias().get(j);
								if (j != (len - 1))
								{
									strTable = strTable + ",";
								}
							}
							String strComboQuery = "select " + objComboDtl.getTextField() + ","
									+ objComboDtl.getValueField() + " from " + strTable + " where ";

							////////adding hospital code clause//////////////////////////////
							strComboQuery = setHospitalCode(strComboQuery, objComboDtl, _hospitalCode);

							//////////adding general where clause////////

							strComboQuery = strComboQuery + objComboDtl.getGenCondition();

							//System.out.println("ComboQuery::" + strComboQuery);
							if (objComboDtl.getAlDepCondition().size() == 0)
							{
								ArrayList alDatalist = fetchRecord(strComboQuery);
								for (int k = 0; k < alDatalist.size();)
								{
									alTxtField.add(alDatalist.get(k));
									alValField.add(alDatalist.get(k + 1));
									k = k + 2;
								}
							}

						}//end of else ie dynamic
						objCombo = new Combo(objComboDtl.getLabel(), objComboDtl.getDefaultValue(), alValField, alTxtField,
								readonly);
						objCombo.display();
						alComboObj.add(objCombo);

					}//(obj.getClass().equals(Class.forName(this.COMBO_DTL)))

				}//end of try
				catch (Exception e)
				{
					System.out.println("Exception in buildcomboquery" + e);
				}//end of catch

			}//end of for								
		}//end of if(((MasterListDtl)objMasterDtl).getAlControls().size()>0) i.e if there is combo or any other control to be displayed in list page		
		return alComboObj;

	}//endof method form comboquery*/

	public ArrayList fetchRecord(String _query)
	{
		//System.out.println("_query in fetch record::::" + _query);
		ArrayList alDatalist = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		try
		{
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
		//ArrayList alDatalist= objMstBuildDb.fetchRecord(_query);	

	}

}//end of class master builder     
