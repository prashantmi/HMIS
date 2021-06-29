package hisglobal.masterxml.masterworkshop.utils;

import hisglobal.masterxml.masterworkshop.tools.MasterDtl;

import java.util.*;
import org.w3c.dom.*;

public abstract class MasterTreeTraverser
{
	protected Node rootOfTree;
	protected MasterDtl objMasterDtl;

	public static final String CLASS_COMBO_DTL = "hisglobal.masterxml.masterworkshop.tools.ComboDtl";
	public static final String CLASS_TEXT_DTL = "hisglobal.masterxml.masterworkshop.tools.TextDtl";
	public static final String CLASS_TEXTAREA_DTL = "hisglobal.masterxml.masterworkshop.tools.TextAreaDtl";
	public static final String CLASS_RADIOBUTTON_DTL = "hisglobal.masterxml.masterworkshop.tools.RadioDtl";
	public static final String CLASS_CHECKBOX_DTL = "hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl";

	public MasterDtl process()
	{
		//System.out.println("in method process");
		Stack nodeHierarchyDtl = new Stack();
		traverse(rootOfTree, nodeHierarchyDtl);//
		//other Housekeeping Job
		//System.out.println("objMasterDtl" + objMasterDtl);
		return objMasterDtl;
	}

	public void setRootOfTree(org.w3c.dom.Node rootOfTree)
	{
		this.rootOfTree = rootOfTree;
	}

	protected void traverse(Node _node, java.util.Stack _nodeHierarchyDtl)
	{
		//Traversal DoM Logic
		//System.out.println("STack::::" + _nodeHierarchyDtl);
		if (_node.getNodeType() == _node.ELEMENT_NODE)
		{
			//System.out.println("inside if of traverse");
			_nodeHierarchyDtl.push(_node.getNodeName());
			//System.out.println("_node.getNodeName()" + _node.getNodeName());

			NamedNodeMap mapAttrib = _node.getAttributes();
			//System.out.println("mapAttrib.getLength()" + mapAttrib.getLength());
			if (mapAttrib.getLength() > 0)
			{

				processor(_nodeHierarchyDtl, mapAttrib);
			}
		}
		else
		{
			if (_node.getNodeType() == _node.TEXT_NODE)
			{
				//System.out.println("inside if of text");
				processor(_nodeHierarchyDtl, _node);
				//System.out.println("sdnskdndsnmd");
				//_nodeHierarchyDtl.pop();
			}
		}

		Node child = _node.getFirstChild();
		//System.out.println("child" + child);
		while (child != null)
		{
			//System.out.println("sdsbdj");
			traverse(child, _nodeHierarchyDtl);
			child = child.getNextSibling();
			//System.out.println("Child" + ((child == null) ? "null" : child.getNodeName()));
		}//end of while				

		if (_nodeHierarchyDtl.size() > 0 && _node.getNodeType() != Node.TEXT_NODE)
		{
			//System.out.println("_nodeHierarchyDtl.pop():  " + _nodeHierarchyDtl.pop());
			_nodeHierarchyDtl.pop();
		}
		//System.out.println("STack::::"+_nodeHierarchyDtl);
	}//end of traverse    

	public abstract void processor(Stack _nodeHierarchyDetail, NamedNodeMap _namedNodeMap);

	public abstract void processor(Stack _nodeHierarchyDetail, Node _textNode);
}
