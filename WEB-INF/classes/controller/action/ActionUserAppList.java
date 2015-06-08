package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appForm.AppFormDTO;
import user.UserDTO;
import userApp.UserAppDTO;
import userApp.UserAppManager;
import controller.ActionForward;

public class ActionUserAppList implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

			request.setCharacterEncoding("euc-kr");
			UserDTO user = new UserDTO();
			ActionForward forward = new ActionForward();
			
			try {
				HttpSession session = request.getSession();
				user = (UserDTO)session.getAttribute("user");
				
				UserAppManager userAppManager = UserAppManager.getInstance();
				ArrayList<AppFormDTO> userAppFormList = null;
				
				String resultKeyword = request.getParameter("search");
				
				if (resultKeyword != null) {
					userAppFormList = userAppManager.searchUserAppFormList(user.getUserNum(), resultKeyword);
					
					if (userAppFormList != null) {
						request.setAttribute("userAppFormList", userAppFormList);
						}
					else {
						userAppFormList = userAppManager.UserAppFormList(user.getUserNum());
						request.setAttribute("userAppFormList", userAppFormList);	
					}
					
					request.setAttribute("resultKeyword", resultKeyword);
					
					// 이동할 페이지를 결정.
					forward.setPath("left_items.jsp");
					forward.setRedirect(false);
				} else {
					userAppFormList = userAppManager.UserAppFormList(user.getUserNum());

					request.setAttribute("resultKeyword", "");
					request.setAttribute("userAppFormList", userAppFormList);
				
					// 이동할 페이지를 결정.
					forward.setPath("left_items.jsp");
					forward.setRedirect(false);
				}
			} catch (Exception e) {
				request.setAttribute("exception", e);
				forward.setPath("left_items.jsp");
				forward.setRedirect(false);
			}
			
			return forward;	
			
		}
}
