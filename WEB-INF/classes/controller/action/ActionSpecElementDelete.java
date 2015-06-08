package controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDTO;
import userSpec.UserSpecDTO;
import userSpec.UserSpecManager;
import classes.LanguageLevel;
import controller.ActionForward;

public class ActionSpecElementDelete implements Action {
	public static String splitNumber(String s) {
		String[] split = s.split("\\D+");
		return split[1];

	}

	public static String splitString(String s) {
		String[] split = s.split("\\d+");
		return split[0];

	}

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("euc-kr");
		// UserSpecDTO recvUserSpec = new UserSpecDTO();
		UserSpecDTO userSpec = null;

		ActionForward forward = new ActionForward();
		UserDTO user = null;
		try {
			request.setCharacterEncoding("euc-kr");
			HttpSession session = request.getSession();
			UserSpecManager userSpecManager = UserSpecManager.getInstance();

			userSpec = new UserSpecDTO();
			user = (UserDTO) session.getAttribute("user");
			
			String delSpec = request.getParameter("delSpecElement");
		
			String delSpecName = splitString(delSpec);
			String delSpecNumStr = splitNumber(delSpec);
			
			int delSpecNum = Integer.parseInt(delSpecNumStr);
			
			if (delSpecName.equals("LanguageLevels")) {
				String LanguageType = request.getParameterValues("LanguageType")[delSpecNum];
				userSpecManager.removeLanguageLevel(user.getUserNum(), LanguageType);
			} else if (delSpecName.equals("LanguageTests")) {
				String TestName = request.getParameterValues("TestName")[delSpecNum];
				userSpecManager.removeLanguageTest(user.getUserNum(), TestName);
			} else if (delSpecName.equals("Licences")) {
				String LicenceName = request.getParameterValues("LicenceName")[delSpecNum];
				userSpecManager.removeLicence(user.getUserNum(), LicenceName);
			} else if (delSpecName.equals("Awards")) {
				String Awardname = request.getParameterValues("Awardname")[delSpecNum];
				userSpecManager.removeAward(user.getUserNum(), Awardname);
			} else if (delSpecName.equals("Volunteers")) {
				String VolunteerInstitution = request.getParameterValues("VolunteerInstitution")[delSpecNum];
				userSpecManager.removeVolunteer(user.getUserNum(), VolunteerInstitution);
			} else if (delSpecName.equals("Careers")) {
				String CompanyName = request.getParameterValues("CompanyName")[delSpecNum];
				userSpecManager.removeCareer(user.getUserNum(), CompanyName);
			}

			userSpec = userSpecManager.getUserSpec(user.getUserNum());
			request.setAttribute("userSpec", userSpec);

			// 이동할 페이지를 결정.
			forward.setPath("user_spec_write.jsp");
			forward.setRedirect(false);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			forward.setPath("user_spec_write.jsp");
			forward.setRedirect(false);
		}

		return forward;
	}
}
