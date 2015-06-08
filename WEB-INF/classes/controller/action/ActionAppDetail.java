package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appForm.AppFormDTO;
import appForm.AppFormManager;
import controller.ActionForward;

public class ActionAppDetail implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

			ActionForward forward = new ActionForward();
			int AppFormNum = Integer.parseInt(request.getParameter("AppFormNum"));
			AppFormDTO appForm;
			
			try {
				AppFormManager appFormManager = AppFormManager.getInstance();
				appForm = appFormManager.getAppForm(AppFormNum);
				request.setAttribute("appForm", appForm);
			}
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				forward.setPath("employeement_detail.jsp");
				forward.setRedirect(false);
			}

			forward.setPath("employeement_detail.jsp");
			forward.setRedirect(false);
			return forward;	
			
		}
}
