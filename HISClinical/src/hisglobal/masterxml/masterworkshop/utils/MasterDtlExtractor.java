package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.masterxml.masterworkshop.tools.MasterDtl;

import org.w3c.dom.*;

public abstract class MasterDtlExtractor
{
	final protected String commonTreeRootName = "common-values";
	protected MasterDtl objMasterDtl;
	protected Node treeRoot;

	public abstract MasterDtl getMasterDtl();
	//public void setTreeRoot( org.w3c.dom.Node treeRoot ) {
	//this.treeRoot = treeRoot;
	//}    
}
