 package billing.masters.controller.action;
    /* Group Master ACTION
     * author: Debashis Sardar
     * Created on : 09-Sep-2011
     */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import billing.masters.controller.data.GroupMstDATA;
import billing.masters.controller.fb.GroupMstFB;
import billing.masters.controller.util.GroupMstUTL;
	



	public class GroupMstACTION extends GenericController {

		String target = null;

		public GroupMstACTION() {

			super(new GroupMstUTL(), "masters/CNTGrpMst");

		}

		/**
		 * forwards control to the Add Page
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return ActionForward object with target
		 * @throws HisException
		 * @throws SQLException
		 */
		
		public ActionForward ADD(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException 
				{
			generateToken(request);
			try
			{
			GroupMstDATA data = new GroupMstDATA();
			GroupMstFB fb = (GroupMstFB) form;
			fb.reset(mapping, request);
			String chk1=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			fb.setStrGroupCombo(data.getGlobalGroupCombo(chk1,fb)) ;
			data.addRecord(fb);
			fb.setStrisVisible("1");
			}
			catch (Exception e) {
			}
			target = "add";
			return mapping.findForward(target);

		}
		/**
		 * invokes insert Logic and forwards control to the Add Page
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return ActionForward object with target
		 * @throws HisException
		 * @throws SQLException
		 */
		
		public ActionForward SAVE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {
			validateToken(request, response);
			int count=-1;
			String errmsg = "";
			String msg = "";
			
			GroupMstDATA data = new GroupMstDATA();
			GroupMstFB fb = (GroupMstFB) form;
			fb.setStrhospitalcode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			fb.setStrseatId(request.getSession().getAttribute("SEATID").toString());
			count=data.InsertRecord(fb);
			if (count==3) {
				
				errmsg = "Group name already exist!";
				fb.setWarnings(errmsg);
				
			
			} 

	        if (count==4) {
				
				errmsg = "Global Group name already Available!";
				fb.setWarnings(errmsg);
				
			
			} 
	      if (count==5) {
				
				errmsg = "Global group name and Group name already exist!";
				fb.setWarnings(errmsg);
				
			
			} 
				if (count==1) {
					errmsg = "Record not Saved!";
					fb.setStrErrorMsg(errmsg);
					
				} 
				if (count==2) {
					msg = "Record Saved Successfully!";
					fb.setMsg(msg);
					
				}
			
			return this.ADD(mapping, form, request, response);

		}
		/**
		 * forwards control to the  Modify Page
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return ActionForward object with target
		 * @throws HisException
		 * @throws SQLException
		 */
		
		public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {
			generateToken(request);
			try {
				GroupMstDATA data = new GroupMstDATA();
				GroupMstFB fb = (GroupMstFB) form;
				data.modifyRecord(request.getParameter("chk"), fb);
				fb.setChk1(request.getParameter("chk"));
			} catch (Exception e) {
			}
			target = "modify";
			return mapping.findForward(target);

		}
		/**
		 * invokes update Logic and forwards control to the list or
		 * Modify Page based on the Logic output
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return ActionForward object with target
		 * @throws HisException
		 * @throws SQLException
		 */
		
		public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {
			validateToken(request, response);
			{	
	            int count=-1;
				GroupMstDATA data = new GroupMstDATA();
				GroupMstFB fb = (GroupMstFB) form;
				
				 fb.setStrseatId(request.getSession().getAttribute("SEATID").toString());
				 fb.setChk1(request.getParameter("chk1"));  
				 fb.setStrEffectiveFrom(request.getParameter("strCrntDate"));
			     count= data.updateRecord(fb.getChk1(), fb);
			if (count==0) {
				String errmsg = "Record can not be modified due to duplicacy!";
				fb.setStrErrorMsg(errmsg);
				target = "modify";
				
			} if(count==1) {
				
					String errmsg = "Record not modified successfully!";
					fb.setStrErrorMsg(errmsg);
					target = "modify";
			}
			if(count==2)
			{
				target="list";
			}
				if (target.equals("list"))
					return this.LIST(mapping, form, request, response);
				else
					return mapping.findForward(target);

			}
		}
	}

	

