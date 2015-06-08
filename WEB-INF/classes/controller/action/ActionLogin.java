package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;

public class ActionLogin implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		if (request.getMethod().equals("GET")) { // request a login form
			ActionForward forward = new ActionForward();
			forward.setPath("login.m2");
			forward.setRedirect(true);
			return forward;
		}

		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		ActionForward forward = new ActionForward();
		UserDTO user = null;
		
		try {
			// 모델에 로그인 처리를 위임.
			UserManager manager = UserManager.getInstance();
			manager.login(userEmail, password);
			
			
			user = manager.findUser(userEmail);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			
			// 이동할 페이지를 결정.
			forward.setPath("main_frame.jsp");
			forward.setRedirect(true);
			
			

		} catch (Exception e) {
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);
		}

		return forward;
	}
}
