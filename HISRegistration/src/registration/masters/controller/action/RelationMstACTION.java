/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;

import hisglobal.masterutil.GenericController;
import registration.masters.controller.util.RelationMstUTIL;
import vo.registration.RelationVO;


/**
 * @author s.singaravelan
 *
 */
public class RelationMstACTION extends GenericController 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private RelationVO relationModel=new RelationVO();
	public HttpServletRequest request = null;
	
	
	 public RelationMstACTION() 
	 {
		 super(new RelationMstUTIL(),"Relation","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("Relation");
		return "report";
	 } 
		
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public RelationVO getRelationModel() 
	{
		return relationModel;
	}

	public void setRelationModel(RelationVO relationModel) 
	{
		this.relationModel = relationModel;
	}

}
