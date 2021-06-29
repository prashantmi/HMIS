package hisglobal.masterxml.masterworkshop.utils;

import org.w3c.dom.*;
import java.util.*;

import hisglobal.masterxml.masterworkshop.tools.MasterDtl;
import hisglobal.masterxml.masterworkshop.tools.MasterListDtl;

public class MasterListDtlExtractor extends MasterDtlExtractor
{
	final protected String specializedTreeRootName = "list-page";

	//constructor specifying the treeRoot to start Extraction

	public MasterListDtlExtractor(Node _treeRoot)
	{
		super.treeRoot = _treeRoot;
	}

	public MasterDtl getMasterDtl()
	{
		objMasterDtl = new MasterListDtl();
		MasterListTreeTraverser objMstLstTraverser = null;
		//System.out.println("in get master detail::: " + objMasterDtl);
		//set the common detail
		//System.out.println("commonTreeRootName::::" + commonTreeRootName);
		Node commonDtlNode = getNode(this.commonTreeRootName);
		//System.out.println("commonDtlNode::::" + commonDtlNode.getNodeType());

		if (commonDtlNode != null)
		{
			//System.out.println("commonDtlNode:  " + objMasterDtl);
			objMstLstTraverser = new MasterListTreeTraverser(commonDtlNode, objMasterDtl);
			objMstLstTraverser.process();

			//traverse thru this tree to set common master details    
		}
		//System.out.println("after traversing tree for common node");
		//set Specialized Detials		
		Node specializeDtlNode = getNode(this.specializedTreeRootName);
		if (specializeDtlNode != null)
		{

			objMstLstTraverser.setRootOfTree(specializeDtlNode);
			objMstLstTraverser.process();
		}
		//System.out.println("objMasterDtl in list extractor" + objMasterDtl);
		return objMasterDtl;
	}

	protected Node getNode(String _nodeName)
	{
		//System.out.println("in get Node");
		//System.out.println("node name:::::" + _nodeName);
		//System.out.println("treeRoot.getNodeName()::::" + treeRoot.getNodeName());
		if (treeRoot.getNodeName().equalsIgnoreCase(_nodeName))
		{
			return treeRoot;
		}
		ArrayList al = new ArrayList();
		al.add(this.treeRoot);
		return searchNode(_nodeName, al);
	}

	protected Node searchNode(String _nodeName, ArrayList queue)
	{// BFS
		//System.out.println("in search node");
		//System.out.println("node name q search" + _nodeName);
		//System.out.println("queue:::::" + queue.size());
		if (queue.size() == 0)
		{//stop Recursion
			return null;
		}
		Node root = (Node) queue.remove(0); // first element in the queue	   

		// if elements left to traverse
		for (Node nd = root.getFirstChild(); nd != null; nd = nd.getNextSibling())
		{
			if (nd.getNodeType() == Node.ELEMENT_NODE) if (nd.getNodeName().equalsIgnoreCase(_nodeName)) return nd;
			else queue.add(nd);
		}

		return searchNode(_nodeName, queue);
	}
}
