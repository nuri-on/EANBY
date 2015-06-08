package controller.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import user.UserDTO;
import user.UserManager;
import controller.ActionForward;

public class ActionCheckEmail implements Action {
	public ActionForward execute(
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		
		if (request.getMethod().equals("GET")) { // request a login form
			ActionForward forward = new ActionForward();
			forward.setPath("login.m2");
			forward.setRedirect(true);
			return forward;
		}

		String userEmail = request.getParameter("userEmail");
		ActionForward forward = new ActionForward();
		UserDTO user = null;
		
		try {
			// �𵨿� �α��� ó���� ����.
			UserManager manager = UserManager.getInstance();
			user = manager.findUser(userEmail);
			PrintWriter out = response.getWriter();
			if (user != null) {
				out.write("alert('�ߺ��Ǿ����ϴ�.')");
			}
			else {
				out.write("alert('����� ���ִ� ���̵��Դϴ�.')");
			}
				
			// �̵��� �������� ����.
			forward.setPath("user_write.jsp");
			forward.setRedirect(false);
			out.flush();
			return forward;

		} catch (Exception e) {
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);
		}
			
		return forward;	
		
		
	}
}