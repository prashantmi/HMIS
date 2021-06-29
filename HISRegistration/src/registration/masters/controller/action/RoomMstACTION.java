/**
 * 
 */
package registration.masters.controller.action;

import javax.servlet.http.HttpServletRequest;



import hisglobal.masterutil.GenericController;
import registration.masters.controller.data.RoomMstDATA;
import registration.masters.controller.util.RoomMstUTIL;
import vo.registration.RoomVO;


/**
 * @author s.singaravelan
 *
 */
public class RoomMstACTION extends GenericController 
{
	private static final long serialVersionUID = 1L;
	private String message;
	private RoomVO roomModel=new RoomVO();
	public HttpServletRequest request = null;
	private String flagAddMod;


	public RoomMstACTION() 
	{
		super(new RoomMstUTIL(),"Room","registration");
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}

	public String report()
	{
		super.REPORT("Room");
		return "report";
	} 



	public String save()
	{
		if(RoomMstDATA.saveRoomDtl(roomModel, "1"))
		{
			try
			{
				roomModel.reset();
				flagAddMod="ADD";
				message="Data added successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return "input";
		}
		else
		{
			this.addActionMessage(roomModel.getStrWarning());
			flagAddMod="ADD";
			return "input";
		}
	}

	public String add()
	{
		try
		{			 
			RoomMstDATA.getRoomEssentials();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}

	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		RoomMstDATA.getRoomEssentials();
		roomModel=RoomMstDATA.modifyRecord(request);
		flagAddMod="MODIFY";	 
		return "input";
	}

	public String update()
	{
		if(RoomMstDATA.updateRoomDtl(roomModel, "2"))
		{
			try
			{
				super.LIST();
				message="Data modified successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else
		{
			this.addActionMessage(roomModel.getStrWarning());
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

	public RoomVO getRoomModel() 
	{
		return roomModel;
	}

	public void setRoomModel(RoomVO roomModel) 
	{
		this.roomModel = roomModel;
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
