package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;


	import hisglobal.masterutil.GenericController;




	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import new_investigation.masters.controller.fb.TestMstFB;
	import new_investigation.masters.controller.fb.filmMstFB;
	import new_investigation.masters.controller.fb.invAddendumReasonMstFB;
	import new_investigation.masters.controller.utl.FilmMstUTL;
	import new_investigation.masters.controller.utl.TestMstUTL;
	import new_investigation.masters.controller.utl.invAddendumReasonMstUTL;

	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;

	import hisglobal.masterutil.GenericController;
	import hisglobal.presentation.ControllerUTIL;
	import hisglobal.presentation.WebUTIL;


	public class invAddendumReasonMstACT extends GenericController {



			String target = null;
		    String message="";
		    
			public invAddendumReasonMstACT()
			{

				super(new invAddendumReasonMstUTL(),"/masters/addendumreason");

			}
		 
			public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			{
				invAddendumReasonMstFB fb = (invAddendumReasonMstFB) form;
				WebUTIL.refreshTransState(request);
				ControllerUTIL.setSysdate(request);
				
				//invAddendumReasonMstUTL.fetchOrgaicName(fb, request);
				return mapping.findForward("ADD");
			}

			public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			{
				invAddendumReasonMstFB fb = (invAddendumReasonMstFB) form;
				
			    boolean hasFlag=  invAddendumReasonMstUTL.saveAddendum(fb, request);
			     if(hasFlag){
			    	 fb.reset(mapping, request);
			     }
				return mapping.findForward("ADD");
			}


			public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			{
					invAddendumReasonMstFB fb = (invAddendumReasonMstFB) form;
				WebUTIL.refreshTransState(request);
				invAddendumReasonMstUTL.fetchAddendum(fb, request);
				return mapping.findForward("ADD"); 
			}
		  
			public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			{
				invAddendumReasonMstFB fb = (invAddendumReasonMstFB) form;
				boolean hasFlag= invAddendumReasonMstUTL.savemodifyAddendum(fb, request);
				if(hasFlag){
				/*	LIST(mapping, fb, request, response);
					return mapping.findForward("LIST");
	*/
					 return this.LIST(mapping,fb, request, response);
				}
				else
					fb.setHmode("MODIFY");
					return mapping.findForward("ADD"); 
			}

			
			public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			{
					invAddendumReasonMstFB fb = (invAddendumReasonMstFB) form;
					invAddendumReasonMstUTL.reFetchAddendum(fb, request);
				fb.setHmode("MODIFY");
				return mapping.findForward("ADD"); 


			}
			
		
		}
		

	
	

