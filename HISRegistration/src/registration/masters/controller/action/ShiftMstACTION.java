/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.ShiftMstDATA;
import registration.masters.controller.util.ShiftMstUTIL;
import vo.registration.ShiftVO;


/**
 * @author s.singaravelan
 *
 */
public class ShiftMstACTION extends GenericController 
{
	private String message;
	private ShiftVO shiftModel=new ShiftVO();
	public HttpServletRequest request = null;
	
	private String flagAddMod;
	
	 public ShiftMstACTION() 
	 {
		 super(new ShiftMstUTIL(),"ShiftMst","registration");
	 }
	 
	 public String execute()
	 {
		super.LIST();
		return SUCCESS;
	 }
	 
	 public String report()
	 {
		super.REPORT("ShiftMst");
		return "report";
	 } 
	 

	 
	 public String add()
	 {
		 flagAddMod="ADD";
		 return "input";
	 }
	 
	 public String save()
	 {
		 if(ShiftMstDATA.saveShiftDtl(shiftModel, "1"))
		 {
			 try
			 {
				shiftModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 this.addActionMessage(shiftModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 
	 public String modify()
	 {
		 HttpServletRequest request= super.getRequest();
		 shiftModel=ShiftMstDATA.modifyRecord(request);
		 flagAddMod="MODIFY";
		 return "input";
	 }
	 
	 public String update()
	 {
		 if(ShiftMstDATA.updateShiftDtl(shiftModel, "2"))
		 {
			 try
			 {
				 super.LIST();
				 message="Data modified successfully";
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 return SUCCESS;
		 }
		 else
		 {
			 this.addActionMessage(shiftModel.getStrWarning());
			 flagAddMod="MODIFY";
			 return "input";
		 }
		 
	}
		
		
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public ShiftVO getShiftModel() 
	{
		return shiftModel;
	}

	public void setShiftModel(ShiftVO shiftModel) 
	{
		this.shiftModel = shiftModel;
	}

	public String getFlagAddMod()
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod)
	{
		this.flagAddMod = flagAddMod;
	}

}
