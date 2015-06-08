package controller.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appForm.AppFormDTO;
import appForm.AppFormManager;
import classes.*;
import user.UserDTO;
import userApp.UserAppDTO;
import userApp.UserAppManager;
import controller.ActionForward;

public class ActionUserAppUpdate implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

			request.setCharacterEncoding("euc-kr"); 
			
			ActionForward forward = new ActionForward();
			UserAppDTO userApp = null;
			UserDTO user = null;
			ArrayList<SelfIntroduction> selfintroductions = null;
			int AppFormNum = Integer.parseInt(request.getParameter("AppFormNum"));
			AppFormDTO appForm;
			
			try {
				HttpSession session = request.getSession();
				UserAppManager userAppManager = UserAppManager.getInstance();
				user = (UserDTO)session.getAttribute("user");
				int UserNum = user.getUserNum();
				
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				String Name1 = request.getParameter("Name1");
				String Name2 = request.getParameter("Name2");
				String Name3 = request.getParameter("Name3");
				String Birthday = request.getParameter("Birthday");
				String Address = request.getParameter("Address");
				String Email = request.getParameter("Email");
				String Phone1 = request.getParameter("Phone1");
				String Phone2 = request.getParameter("Phone2");
				
				UserInfo userInfo = new UserInfo();
				userInfo.setName1(Name1);
				userInfo.setName2(Name2);
				userInfo.setName3(Name3);
				if (Birthday != null) {
					userInfo.setBirthDay(transFormat.parse(Birthday));
				}
				userInfo.setAddress(Address);
				userInfo.setEmail(Email);
				userInfo.setPhone1(Phone1);
				userInfo.setPhone2(Phone2);				
				userAppManager.createAppUserInfo(userInfo, UserNum, AppFormNum);
				
				String LanguageType[] = request.getParameterValues("LanguageType");
				String ConversationLevel[] = request.getParameterValues("ConversationLevel");
				String ReadingLevel[] = request.getParameterValues("ReadingLevel");
				String WritingLevel[] = request.getParameterValues("WritingLevel");

				if (LanguageType != null) { 
					for (int i = 0; i < LanguageType.length; i++) {
						LanguageLevel languageLevel = new LanguageLevel();
						languageLevel.setUserNum(user.getUserNum());
						if (LanguageType[i] == "")
							break;
						languageLevel.setLanguageType(LanguageType[i]);
						languageLevel.setConversationLevel(ConversationLevel[i]);
						languageLevel.setReadingLevel(ReadingLevel[i]);
						languageLevel.setWritingLevel(WritingLevel[i]);
						userAppManager.createAppLanguageLevel(languageLevel, UserNum, AppFormNum);
					}
				}
				
				
				String TestName[] = request.getParameterValues("TestName");
				String TestScore[] = request.getParameterValues("TestScore");
				String TestLevel[] = request.getParameterValues("TestLevel");
				String TestDay[] = request.getParameterValues("TestDay");
				
				if (TestName != null) { 
					for (int i = 0; i < TestName.length; i++) {
						LanguageTest languageTest = new LanguageTest();
						languageTest.setUserNum(user.getUserNum());
						if (TestName[i] == "")
							break;
						languageTest.setTestName(TestName[i]);
						languageTest.setTestScore(TestScore[i]);
						languageTest.setTestLevel(TestLevel[i]);
						languageTest.setTestDay(transFormat.parse(TestDay[i]));
						
						userAppManager.createAppLanguageTest(languageTest, UserNum, AppFormNum);
					}
				}
				
				String LicenceName[] = request.getParameterValues("LicenceName");
				String LicenceInstitution[] = request.getParameterValues("LicenceInstitution");
				String LicenceScore[] = request.getParameterValues("LicenceScore");
				String Licenceday[] = request.getParameterValues("Licenceday");
				
				if (LicenceName != null) { 
					for (int i = 0; i < LicenceName.length; i++) {
						Licence licence = new Licence();
						licence.setUserNum(user.getUserNum());
						if (LicenceName[i] == "")
							break;
						licence.setLicenceName(LicenceName[i]);;
						licence.setLicenceInstitution(LicenceInstitution[i]);
						licence.setLicenceScore(LicenceScore[i]);
						licence.setLicenceday(transFormat.parse(Licenceday[i]));
						userAppManager.createAppLicence(licence, UserNum, AppFormNum);
					}
					
				}
				
				String Awardname[] = request.getParameterValues("Awardname");
				String Awarddetails[] = request.getParameterValues("Awarddetails");
				String Awardinstitution[] = request.getParameterValues("Awardinstitution");
				String Awardday[] = request.getParameterValues("Awardday");
				
				if (Awardname != null) { 
					for (int i = 0; i < Awardname.length; i++) {
						Award award = new Award();
						award.setUserNum(user.getUserNum());
						if (Awardname[i] == "")
							break;
						award.setAwardname(Awardname[i]);
						award.setAwarddetails(Awarddetails[i]);
						award.setAwardinstitution(Awardinstitution[i]);
						award.setAwardday(transFormat.parse(Awardday[i]));
						userAppManager.createAppAward(award, UserNum, AppFormNum);
					}
				}
				
				/*String CountryName[] = request.getParameterValues("CountryName");
				String PurposeAndContents[] = request.getParameterValues("PurposeAndContents");
				String StayStartDay[] = request.getParameterValues("StayStartDay");
				String StayEndDay[] = request.getParameterValues("StayEndDay");
				
				ArrayList<ForeignStudy> specForeignStudies = new ArrayList<>();
				if (CountryName != null) {
					for (int i = 0; i < CountryName.length; i++) {
						ForeignStudy foreignStudy = new ForeignStudy();
						foreignStudy.setUserNum(user.getUserNum());
						if (CountryName[i] == "")
							break;
						foreignStudy.setCountryName(CountryName[i]);
						foreignStudy.setPurposeAndContents(PurposeAndContents[i]);
						foreignStudy.setStayStartDay(transFormat.parse(StayStartDay[i]));
						foreignStudy.setStayEndDay(transFormat.parse(StayEndDay[i]));
						//userAppManager.createAppForeignStudy(foreignStudy, UserNum, AppFormNum);
						specForeignStudies.add(foreignStudy);
					}
					//userSpec.setSpecForeignStudies(specForeignStudies);
				}*/
				
				String HighschoolName = request.getParameter("HighschoolName");
				String HEntanceDay = request.getParameter("HEntanceDay");
				String HGraduateDay = request.getParameter("HGraduateDay");
				
				Highschool userSpecHighschool = new Highschool();
				userSpecHighschool.setUserNum(user.getUserNum());
				userSpecHighschool.setHighschoolName(HighschoolName);
				if (HEntanceDay != null) {
					if (!HEntanceDay.equals("")) {
						userSpecHighschool.setEntanceDay(transFormat.parse(HEntanceDay));
					}
				}
				if (HGraduateDay != null) {
					if (!HGraduateDay.equals("")) {
						userSpecHighschool.setGraduateDay(transFormat.parse(HGraduateDay));
					}
				}
				userAppManager.createAppHighschool(userSpecHighschool, UserNum, AppFormNum);
			
				String UniversityName = request.getParameter("UniversityName");
				String UEntranceDay = request.getParameter("UEntranceDay");
				String UGraudateDay = request.getParameter("UGraudateDay");
				String Major = request.getParameter("Major");
				String Grade = request.getParameter("Grade");
				String Credit = request.getParameter("Credit");
				
				University userAppUniversity = new University();
				userAppUniversity.setUserNum(user.getUserNum());
				userAppUniversity.setUniversityName(UniversityName);
				if (UEntranceDay != null) {
					if (!UEntranceDay.equals("")) {
						userAppUniversity.setEntranceDay(transFormat.parse(UEntranceDay));
					} 
				}
				if (UGraudateDay != null) {
					if (!UGraudateDay.equals("")) {
						userAppUniversity.setGraudateDay(transFormat.parse(UGraudateDay));
					}
				}
				userAppUniversity.setMajor(Major);
				userAppUniversity.setGrade(Grade);
				userAppUniversity.setCredit(Credit);
				userAppManager.createAppUniversity(userAppUniversity, UserNum, AppFormNum);
				
				String VolunteerDetails[] = request.getParameterValues("VolunteerDetails");
				String VolunteerStartDay[] = request.getParameterValues("VolunteerStartDay");
				String VolunteerEndDay[] = request.getParameterValues("VolunteerEndDay");
				String VolunteerInstitution[] = request.getParameterValues("VolunteerInstitution");
				
				if (VolunteerDetails != null) {
					for (int i = 0; i < VolunteerDetails.length; i++) {
						Volunteer volunteer = new Volunteer();
						volunteer.setUserNum(user.getUserNum());
						if (VolunteerDetails[i] == "")
							break;
						volunteer.setVolunteerDetails(VolunteerDetails[i]);
						volunteer.setVolunteerStartDay(transFormat.parse(VolunteerStartDay[i]));
						volunteer.setVolunteerEndDay(transFormat.parse(VolunteerEndDay[i]));
						volunteer.setVolunteerInstitution(VolunteerInstitution[i]);
						userAppManager.createAppVolunteer(volunteer, UserNum, AppFormNum);
					}
				}
				
				String CareerType[] = request.getParameterValues("CareerType");
				String CareerStartDay[] = request.getParameterValues("CareerStartDay");
				String CareerEndDay[] = request.getParameterValues("CareerEndDay");
				String CareerDpName[] = request.getParameterValues("CareerDpName");
				String AnnualSalary[] = request.getParameterValues("AnnualSalary");
				String CompanyName[] = request.getParameterValues("CompanyName");
				String Position[] = request.getParameterValues("Position");
				
				if (CareerType != null) {
					for (int i = 0; i < CareerType.length; i++) {
						Career career = new Career();
						career.setUserNum(user.getUserNum());
						if (CareerType[i] == "")
							break;
						career.setCareerType(CareerType[i]);
						career.setCareerStartDay(transFormat.parse(CareerStartDay[i]));
						career.setCareerEndDay(transFormat.parse(CareerEndDay[i]));
						career.setCareerDpName(CareerDpName[i]);
						career.setAnnualSalary(AnnualSalary[i]);
						career.setCompanyName(CompanyName[i]);
						career.setPosition(Position[i]);
						userAppManager.createAppCareer(career, UserNum, AppFormNum);
					}
				}
				
				String SelfIntroNum[] = request.getParameterValues("SelfIntroNum");
				String selfIntroContents[] = request.getParameterValues("selfIntroContents");
				
				if (SelfIntroNum != null) {
					for (int i = 0; i < SelfIntroNum.length; i++) {
						SelfIntroduction self = new SelfIntroduction();
						self.setSelfIntroNum(Integer.parseInt(SelfIntroNum[i]));
						self.setSelfIntroContents(selfIntroContents[i]);
						userAppManager.createAppSelfIntroduction(self, UserNum, AppFormNum);
					}
				}
				userApp = userAppManager.getUserApp(UserNum, AppFormNum);
				selfintroductions = userAppManager.getSelfIntrocduction(UserNum, AppFormNum);
				request.setAttribute("userApp", userApp);
				request.setAttribute("selfintroductions", selfintroductions);
				request.setAttribute("haveUserApp", "y");
				
				AppFormManager appFormManager = AppFormManager.getInstance();
				appForm = appFormManager.getAppForm(AppFormNum);
				request.setAttribute("appForm", appForm);
				
				selfintroductions = userAppManager.getSelfIntrocduction(UserNum, AppFormNum);
				
			} 
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				forward.setPath("user_spec_write.jsp");
				forward.setRedirect(false);
			}

			
			forward.setPath("main_insertApp.jsp");
			forward.setRedirect(false);	
			return forward;	
			
		}
}
