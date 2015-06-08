package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;

public class ActionLogout implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

			ActionForward forward = new ActionForward();
			
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			
			forward.setPath("login.jsp");
			forward.setRedirect(true);
				
			return forward;	
			
		}
}
