package controller.action;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appForm.AppFormDTO;
import appForm.AppFormManager;
import controller.ActionForward;

public class ActionAppList implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			
			request.setCharacterEncoding("euc-kr");
			
			ActionForward forward = new ActionForward();
			
			try {
				AppFormManager appFormManager = AppFormManager.getInstance();
				ArrayList<AppFormDTO> appFormList = null;
				
				String page = request.getParameter("page");
		
				if (page != null) {
					// 이동할 페이지를 결정.
					appFormList = appFormManager.AllAppFormList();
					request.setAttribute("appFormList", appFormList);
					forward.setPath("employeement_list.jsp");
					forward.setRedirect(false);
					
				} else {
					// 이동할 페이지를 결정.
					appFormList = appFormManager.AppFormList();
					request.setAttribute("appFormList", appFormList);
					forward.setPath("main_contents.jsp");
					forward.setRedirect(false);
				}
				
			} catch (Exception e) {
				request.setAttribute("exception", e);
				forward.setPath("main_contents.jsp");
				forward.setRedirect(false);
			}
				
			return forward;	
			
		}
}
