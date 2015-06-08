package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import userSpec.UserSpecDAO;
import userSpec.UserSpecDTO;
import userSpec.UserSpecManager;
import controller.ActionForward;

public class ActionMySpec implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			request.setCharacterEncoding("euc-kr"); 
			//if (request.getMethod().equals("POST")) { // request a login form
				//ActionForward forward = new ActionForward();
				//forward.setPath("user_spec_write.jsp");
				//forward.setRedirect(true);
				//return forward;
			//}
						
			UserSpecDTO userSpec = new UserSpecDTO();
			UserSpecDAO userSpecDAO = new UserSpecDAO();
			
			ActionForward forward = new ActionForward();
			UserDTO user = null;
			
			boolean photoExisted = false;
			String photoExistedArg = null;
			
			try {
				HttpSession session = request.getSession();
				UserSpecManager userSpecManager = UserSpecManager.getInstance();
				
				user = (UserDTO)session.getAttribute("user");
				
				String pwd = session.getAttribute("realPath").toString();
				// userNum으로 스펙 정보 가져오기.
				userSpec = userSpecManager.getUserSpec(user.getUserNum());
				photoExisted = userSpecManager.getUserPhoto(user.getUserNum(), pwd);
				
				if (photoExisted)
					photoExistedArg = "true";
				else
					photoExistedArg = "false";
				
				
				// userNum으로 스펙 정보 가져오기.
				userSpec = userSpecManager.getUserSpec(user.getUserNum());
				request.setAttribute("userSpec", userSpec);
				request.setAttribute("userSpecDAO", userSpecDAO);
				request.setAttribute("photoExisted", photoExistedArg);
				
				// 이동할 페이지를 결정.
				forward.setPath("user_spec_write.jsp");
				forward.setRedirect(false);	
				
				
			} catch (Exception e) {
				request.setAttribute("exception", e);
				forward.setPath("user_spec_write.jsp");
				forward.setRedirect(false);
			}

			return forward;
			
		}
}
