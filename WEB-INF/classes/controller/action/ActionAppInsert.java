package controller.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.AppFormUserInfo;
import classes.LanguageLevel;
import classes.SelfIntroduction;
import appForm.*;
import user.UserDTO;
import controller.ActionForward;

public class ActionAppInsert implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			
			request.setCharacterEncoding("euc-kr"); 
			ActionForward forward = new ActionForward();
			HttpSession session = request.getSession();
			UserDTO user = null;
			
			int UserNum;
			
			try {
				AppFormManager appFormManager = AppFormManager.getInstance();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				user = (UserDTO)session.getAttribute("user");
				AppFormDTO appForm = new AppFormDTO();
				AppFormUserInfo appformUserInfo = new AppFormUserInfo();
				user = (UserDTO)session.getAttribute("user");
				UserNum = user.getUserNum();
				
				String posterPath = request.getParameter("profile_pt");
				System.out.println(posterPath);
				appForm.setAppFormPosterPath(posterPath);
				
				String registerName = request.getParameter("registerName");
				String companyName = request.getParameter("companyName");
				String appPreference = request.getParameter("appPreference");
				String appDetail = request.getParameter("appDetail");
				String appStartDay = request.getParameter("appStartDay");
				String appEndDay = request.getParameter("appEndDay");
				String appAwardCount = request.getParameter("appAwardCount");
				String appLanguageLevelCount = request.getParameter("appLanguageLevelCount");
				String appLanguageTestCount = request.getParameter("appLanguageTestCount");
				String appLicenceCount = request.getParameter("appLicenceCount");
				String appVolunteerCount = request.getParameter("appVolunteerCount");
				String appCareerCount = request.getParameter("appCareerCount");

				appForm.setAppFormRegisterName(registerName);
				appForm.setAppFormCompany(companyName);
				appForm.setPreference(appPreference);
				appForm.setDetail(appDetail);
				if (!appStartDay.equals("")) {
					appForm.setAppFormStartDate(transFormat.parse(appStartDay));
				}
				if (!appEndDay.equals("")) {
					appForm.setAppFormEndDate(transFormat.parse(appEndDay));
				}
				
				appForm.setAppFormAwards(Integer.parseInt(appAwardCount));
				appForm.setAppFormLanguageLevel(Integer.parseInt(appLanguageLevelCount));
				appForm.setAppFormLanguageTest(Integer.parseInt(appLanguageTestCount));
				appForm.setAppFormLicences(Integer.parseInt(appLicenceCount));
				appForm.setAppFormVolunteers(Integer.parseInt(appVolunteerCount));
				appForm.setAppFormCareers(Integer.parseInt(appCareerCount));
				
				if (request.getParameter("appUniversityC") != null) {
					appForm.setAppFormUniversity("y");
				}
				else {
					appForm.setAppFormUniversity("n");
				}
				
				if (request.getParameter("appHighschoolC") != null) {
					appForm.setAppFormHighschool("y");
				}
				else {
					appForm.setAppFormHighschool("n");
				}
				
				if (request.getParameter("appName1C") != null) {
					appformUserInfo.setName1("y");
				}
				else {
					appformUserInfo.setName1("n");
				}
				
				if (request.getParameter("appName2C") != null) {
					appformUserInfo.setName2("y");
				}
				else {
					appformUserInfo.setName2("n");
				}
				
				if (request.getParameter("appName3C") != null) {
					appformUserInfo.setName3("y");
				}
				else {
					appformUserInfo.setName3("n");
				}
				
				if (request.getParameter("appBirthdayC") != null) {
					appformUserInfo.setBirthday("y");
				}
				else {
					appformUserInfo.setBirthday("n");
				}
				
				if (request.getParameter("appAddressC") != null) {
					appformUserInfo.setAddress("y");
				}
				else {
					appformUserInfo.setAddress("n");
				}
				
				if (request.getParameter("appEmailC") != null) {
					appformUserInfo.setEmail("y");
				}
				else {
					appformUserInfo.setEmail("n");
				}
				if (request.getParameter("appPhone1C") != null) {
					appformUserInfo.setPhone1("y");
				}
				
				else {
					appformUserInfo.setPhone1("n");
				}
				
				if (request.getParameter("appPhone2C") != null) {
					appformUserInfo.setPhone2("y");
				}
				else {
					appformUserInfo.setPhone2("n");
				}
				
				appForm.setAppFormUserInfo(appformUserInfo);
				
				
				String selfIntro[] = request.getParameterValues("selfIntro");
				String selfIntroBytes[] =request.getParameterValues("selfIntroBytes");
				ArrayList<SelfIntroduction> SelfIntroductions = new ArrayList<SelfIntroduction>();
				if (selfIntro != null) { 
					for (int i = 0; i < selfIntro.length; i++) {
						SelfIntroduction selfintroduction = new SelfIntroduction();
						selfintroduction.setItemName(selfIntro[i]);
						selfintroduction.setIntroSize(Integer.parseInt(selfIntroBytes[i]));
						selfintroduction.setSelfIntroNum(i+1);
						SelfIntroductions.add(selfintroduction);
						
					}
					appForm.setSelfIntroductions(SelfIntroductions);
				}
				
				appFormManager.createAppForm(appForm,UserNum);
				// 이동할 페이지를 결정.
				forward.setPath("main_manager_contents.jsp");
				forward.setRedirect(false);
				
			} 
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				forward.setPath("main_manager_contents.jsp");
				forward.setRedirect(false);
			}

		return forward;
			
		}
}
