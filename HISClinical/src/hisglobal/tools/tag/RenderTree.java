package hisglobal.tools.tag;

import hisglobal.hisconfig.Config;
import hisglobal.tools.Tree;
import hisglobal.tools.TreeBuilder;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.RequestUtils;

public class RenderTree extends TagSupport
{
	public int doStartTag() throws JspTagException
	{
		System.out.println("inside taggggg ");
		TreeBuilder tb;
		// Tree tr = new Tree();
		//ArrayList temp = new ArrayList();
		//ArrayList postdata = new ArrayList();
		//ArrayList temp1 = new ArrayList();
		HttpSession session = (HttpSession) pageContext.getSession();
		Tree diagnosistree = (Tree) session.getAttribute(Config.DIAGNOSIS_TREE);
		try
		{
			if (diagnosistree != null) System.out.println("diagnosistree.postorderTraversalQue.getQueueSize():  "
					+ diagnosistree.getPostorderTraversalQue().getQueueSize());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// Tree
		// diagnosistree=(String[])session.getAttribute(RegistrationConfig.ARR_SELECTED_DIAGNOSIS_CODE);
		// Tree
		// diagnosistree=(Tree)session.getAttribute(RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME);
		diagnosistree.setTreeName(Config.TREE_NAME);
		// RSTree rsTree =new RSTree();
		/*
		 * try{ rsTree.setTreeData(diagnosistree.getRoot()); } catch(Exception e){ e.printStackTrace(); }
		 */

		tb = new TreeBuilder(diagnosistree);
		// Status objStatus= (Status)
		// request.getAttribute(Config.STATUS_OBJECT);
		try
		{

			javax.servlet.jsp.JspWriter out = pageContext.getOut();
			// MyExpTree t=new MyExpTree();

			// TreeNode root = t.callIt();
			System.out.println("1");
			System.out.println("out::" + out);
			System.out.println("diagnosistree::" + diagnosistree);
			// root.setValue("Root");
			// tb.renderTree(out ,root);

			SelectTree parent = (SelectTree) getParent();
			Object bean = RequestUtils.lookup(pageContext, parent.getBeanName(), null);
			String match[] = BeanUtils.getArrayProperty(bean, parent.getPropertyName());
			/*
			 * System.out.println("match"+match); System.out.println("match"+match.length); for(int i=0;i<match.length;i++){
			 * System.out.println("match"+match[i]); } for(int i=0;i<match.length;i++){ try{
			 * diagnosistree.setFindState(match[i]); } catch(Exception e){ e.printStackTrace(); }
			 *  }
			 */
			String propertyName = parent.getPropertyName();
			tb.renderSelectTree(out, propertyName, match);
			/*
			 * System.out.println("2"); temp=tb.getPreorderTraversalQue(); System.out.println("3");
			 * temp1=tb.getPostorderTraversalQue(); System.out.println("4"); postdata=tb.getPostorderData();
			 * System.out.println("5"); pageContext.getRequest().setAttribute("preorderTraversalQue",temp);
			 * pageContext.getRequest().setAttribute("postorderTraversalQue",temp1);
			 * pageContext.getRequest().setAttribute("postorderData",postdata);
			 * pageContext.getRequest().setAttribute("treename",diagnosistree.getTreeName().trim());
			 */
			// pageContext.getRequest().setAttribute("preorderData",predata);
			/*
			 * for(int i=0;i<temp.size();i++){ // System.out.println("the preorder of list // is:"+temp.get(i)); //
			 * System.out.println("the postorder of list // is:"+temp1.get(i)); }
			 */

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
}// end of class
