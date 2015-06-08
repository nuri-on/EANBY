package controller.action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;

public class ActionJoinInsert implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			request.setCharacterEncoding("euc-kr");
			
			UserDTO user = null;
			ActionForward forward = new ActionForward();
			
			try {
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				HttpSession session = request.getSession();
				UserManager userManager = UserManager.getInstance();
				
				user = new UserDTO();
				
				String UserName = request.getParameter("UserName");
				String UserEmail = request.getParameter("UserEmail");
				String UserPhone1 = request.getParameter("UserPhone1");
				String UserPhone2 = request.getParameter("UserPhone2");
				String UserBirth = request.getParameter("UserBirth");
				String UserNickname = request.getParameter("UserNickname");
				String UserPassword = request.getParameter("UserPassword");
				
				user.setUserName(UserName);
				user.setUserEmail(UserEmail);
				user.setUserPhone1(UserPhone1);
				user.setUserPhone2(UserPhone2);
				if (UserBirth != null) {
					user.setUserBirth(transFormat.parse(UserBirth));
				}
				user.setUserNickname(UserNickname);
				user.setUserPassword(UserPassword);
				
				userManager.create(user);
	
				forward.setPath("login.jsp");
				forward.setRedirect(true);
				
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				forward.setPath("user_write.jsp");
				forward.setRedirect(false);
			}			
			return forward;	
			
		}
}
