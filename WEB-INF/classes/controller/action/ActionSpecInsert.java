package controller.action;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.BLOB;
import user.UserDTO;
import userSpec.UserSpecDTO;
import userSpec.UserSpecManager;
import classes.Award;
import classes.Career;
import classes.ForeignStudy;
import classes.Highschool;
import classes.LanguageLevel;
import classes.LanguageTest;
import classes.Licence;
import classes.Photo;
import classes.University;
import classes.UserInfo;
import classes.Volunteer;
import controller.ActionForward;
 
public class ActionSpecInsert implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {				
			request.setCharacterEncoding("euc-kr"); 
			
			//UserSpecDTO recvUserSpec = new UserSpecDTO();
			UserSpecDTO userSpec = null;
			UserSpecDTO userSpec2 = null;
			boolean photoExisted = false;
			String photoExistedArg = null;
			
			ActionForward forward = new ActionForward();
			UserDTO user = null;
			try {
				request.setCharacterEncoding("euc-kr");
				HttpSession session = request.getSession();
				UserSpecManager userSpecManager = UserSpecManager.getInstance();
				
				userSpec = new UserSpecDTO();
				
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				user = (UserDTO)session.getAttribute("user");

				String Name1 = request.getParameter("Name1");
				String Name2 = request.getParameter("Name2");
				String Name3 = request.getParameter("Name3");
				String Birthday = request.getParameter("Birthday");
				String Address = request.getParameter("Address");
				String Email = request.getParameter("Email");
				String Phone1 = request.getParameter("Phone1");
				String Phone2 = request.getParameter("Phone2");
				
				// 이미지!!
				String photoPath = request.getParameter("profile_pt");
				String pwd = (String)session.getAttribute("realPath");
				
				photoExisted = userSpecManager.getUserPhoto(user.getUserNum(), pwd);


				if (photoExisted)
					photoExistedArg = "true";
				else
					photoExistedArg = "false";
				
				request.setAttribute("photoExisted", photoExistedArg);
				
				photoExisted = userSpecManager.getUserPhoto(user.getUserNum(), pwd);
				
				Photo photo = new Photo();
				photo.setPhotoPath(photoPath);	
				photo.setPwd(pwd);
				photo.setPhotoExisted(photoExistedArg);
				
				userSpecManager.createSpecPhoto(photo, user.getUserNum());
				
				UserInfo userInfo = new UserInfo();
				userInfo.setName1(Name1);
				userInfo.setName2(Name2);
				userInfo.setName3(Name3);
				if (!Birthday.equals("null")) {
					userInfo.setBirthDay(transFormat.parse(Birthday));
				}
				userInfo.setAddress(Address);
				userInfo.setEmail(Email);
				userInfo.setPhone1(Phone1);
				userInfo.setPhone2(Phone2);
				
				//userSpec.setSpecUserInfo(userInfo);
				userSpecManager.createSpecUserInfo(userInfo, user.getUserNum());
				
				String LanguageType[] = request.getParameterValues("LanguageType");
				String ConversationLevel[] = request.getParameterValues("ConversationLevel");
				String ReadingLevel[] = request.getParameterValues("ReadingLevel");
				String WritingLevel[] = request.getParameterValues("WritingLevel");
				
				ArrayList<LanguageLevel> specLanguageLevels = new ArrayList<>();
				if (LanguageType != null) { 
					for (int i = 0; i < LanguageType.length; i++) {
						LanguageLevel languageLevel = new LanguageLevel();
						languageLevel.setUserNum(user.getUserNum());
						languageLevel.setLanguageType(LanguageType[i]);
						languageLevel.setConversationLevel(ConversationLevel[i]);
						languageLevel.setReadingLevel(ReadingLevel[i]);
						languageLevel.setWritingLevel(WritingLevel[i]);
						userSpecManager.createSpecLanguageLevel(languageLevel, user.getUserNum());
						specLanguageLevels.add(languageLevel);
					}
					//userSpec.setSpecLanguageLevels(specLanguageLevels);
				}
				
				
				String TestName[] = request.getParameterValues("TestName");
				String TestScore[] = request.getParameterValues("TestScore");
				String TestLevel[] = request.getParameterValues("TestLevel");
				String TestDay[] = request.getParameterValues("TestDay");
				
				ArrayList<LanguageTest> specLanguageTests = new ArrayList<>();
				if (TestName != null) { 
					for (int i = 0; i < TestName.length; i++) {
						LanguageTest languageTest = new LanguageTest();
						languageTest.setUserNum(user.getUserNum());
						languageTest.setTestName(TestName[i]);
						languageTest.setTestScore(TestScore[i]);
						languageTest.setTestLevel(TestLevel[i]);
						languageTest.setTestDay(transFormat.parse(TestDay[i]));
						
						userSpecManager.createSpecLanguageTest(languageTest, user.getUserNum());
						specLanguageTests.add(languageTest);
					}
					//userSpec.setSpecLanguageTests(specLanguageTests);
				}
				
				
				String LicenceName[] = request.getParameterValues("LicenceName");
				String LicenceInstitution[] = request.getParameterValues("LicenceInstitution");
				String LicenceScore[] = request.getParameterValues("LicenceScore");
				String Licenceday[] = request.getParameterValues("Licenceday");
				
				ArrayList<Licence> specLicences = new ArrayList<>();
				if (LicenceName != null) { 
					for (int i = 0; i < LicenceName.length; i++) {
						Licence licence = new Licence();
						licence.setUserNum(user.getUserNum());
						licence.setLicenceName(LicenceName[i]);;
						licence.setLicenceInstitution(LicenceInstitution[i]);
						licence.setLicenceScore(LicenceScore[i]);
						licence.setLicenceday(transFormat.parse(Licenceday[i]));
						userSpecManager.createSpecLicence(licence, user.getUserNum());
						specLicences.add(licence);
					}
					//userSpec.setSpecLicences(specLicences);
				}
				
				String Awardname[] = request.getParameterValues("Awardname");
				String Awarddetails[] = request.getParameterValues("Awarddetails");
				String Awardinstitution[] = request.getParameterValues("Awardinstitution");
				String Awardday[] = request.getParameterValues("Awardday");
				
				ArrayList<Award> specAwards = new ArrayList<>();
				if (Awardname != null) { 
					for (int i = 0; i < Awardname.length; i++) {
						Award award = new Award();
						award.setUserNum(user.getUserNum());
						award.setAwardname(Awardname[i]);
						award.setAwarddetails(Awarddetails[i]);
						award.setAwardinstitution(Awardinstitution[i]);
						award.setAwardday(transFormat.parse(Awardday[i]));
						userSpecManager.createSpecAward(award, user.getUserNum());
						specAwards.add(award);
					}
					//userSpec.setSpecAwards(specAwards);
				}
				
				String CountryName[] = request.getParameterValues("CountryName");
				String PurposeAndContents[] = request.getParameterValues("PurposeAndContents");
				String StayStartDay[] = request.getParameterValues("StayStartDay");
				String StayEndDay[] = request.getParameterValues("StayEndDay");
				
				ArrayList<ForeignStudy> specForeignStudies = new ArrayList<>();
				if (CountryName != null) {
					for (int i = 0; i < CountryName.length; i++) {
						ForeignStudy foreignStudy = new ForeignStudy();
						foreignStudy.setUserNum(user.getUserNum());
						foreignStudy.setCountryName(CountryName[i]);
						foreignStudy.setPurposeAndContents(PurposeAndContents[i]);
						foreignStudy.setStayStartDay(transFormat.parse(StayStartDay[i]));
						foreignStudy.setStayEndDay(transFormat.parse(StayEndDay[i]));
						userSpecManager.createSpecForeignStudy(foreignStudy, user.getUserNum());
						specForeignStudies.add(foreignStudy);
					}
					//userSpec.setSpecForeignStudies(specForeignStudies);
				}
				
				String HighschoolName = request.getParameter("HighschoolName");
				String HEntanceDay = request.getParameter("HEntanceDay");
				String HGraduateDay = request.getParameter("HGraduateDay");
				
				Highschool specHighschool = new Highschool();
				specHighschool.setUserNum(user.getUserNum());
 				specHighschool.setHighschoolName(HighschoolName);
				if (!HEntanceDay.equals("")) {
					specHighschool.setEntanceDay(transFormat.parse(HEntanceDay));
				}
				if (!HGraduateDay.equals("")) {
					specHighschool.setGraduateDay(transFormat.parse(HGraduateDay));
				}
				userSpecManager.createSpecHighschool(specHighschool, user.getUserNum());
				//userSpec.setSpecHighschool(specHighschool);
		
				String UniversityName = request.getParameter("UniversityName");
				String UEntranceDay = request.getParameter("UEntranceDay");
				String UGraudateDay = request.getParameter("UGraudateDay");
				String Major = request.getParameter("Major");
				String Grade = request.getParameter("Grade");
				String Credit = request.getParameter("Credit");
				
				University specUniversity = new University();
				specUniversity.setUserNum(user.getUserNum());
				specUniversity.setUniversityName(UniversityName);
				if (!UEntranceDay.equals("")) {
					specUniversity.setEntranceDay(transFormat.parse(UEntranceDay));
				} 
				if (!UGraudateDay.equals("")) {
					specUniversity.setGraudateDay(transFormat.parse(UGraudateDay));
				}
				specUniversity.setMajor(Major);
				specUniversity.setGrade(Grade);
				specUniversity.setCredit(Credit);
				userSpecManager.createSpecUniversity(specUniversity, user.getUserNum());
				//userSpec.setSpecUniversity(specUniversity);
				
				String VolunteerDetails[] = request.getParameterValues("VolunteerDetails");
				String VolunteerStartDay[] = request.getParameterValues("VolunteerStartDay");
				String VolunteerEndDay[] = request.getParameterValues("VolunteerEndDay");
				String VolunteerInstitution[] = request.getParameterValues("VolunteerInstitution");
				
				ArrayList<Volunteer> specVolunteers = new ArrayList<>();
				if (VolunteerDetails != null) {
					for (int i = 0; i < VolunteerDetails.length; i++) {
						Volunteer volunteer = new Volunteer();
						volunteer.setUserNum(user.getUserNum());
						volunteer.setVolunteerDetails(VolunteerDetails[i]);
						volunteer.setVolunteerStartDay(transFormat.parse(VolunteerStartDay[i]));
						volunteer.setVolunteerEndDay(transFormat.parse(VolunteerEndDay[i]));
						volunteer.setVolunteerInstitution(VolunteerInstitution[i]);
						userSpecManager.createSpecVolunteer(volunteer, user.getUserNum());
						specVolunteers.add(volunteer);
					}
					//userSpec.setSpecVolunteers(specVolunteers);
				}
				
				String CareerType[] = request.getParameterValues("CareerType");
				String CareerStartDay[] = request.getParameterValues("CareerStartDay");
				String CareerEndDay[] = request.getParameterValues("CareerEndDay");
				String CareerDpName[] = request.getParameterValues("CareerDpName");
				String AnnualSalary[] = request.getParameterValues("AnnualSalary");
				String CompanyName[] = request.getParameterValues("CompanyName");
				String Position[] = request.getParameterValues("Position");
				
				ArrayList<Career> specCareers = new ArrayList<>();
				if (CareerType != null) {
					for (int i = 0; i < CareerType.length; i++) {
						Career career = new Career();
						career.setUserNum(user.getUserNum());
						career.setCareerType(CareerType[i]);
						career.setCareerStartDay(transFormat.parse(CareerStartDay[i]));
						career.setCareerEndDay(transFormat.parse(CareerEndDay[i]));
						career.setCareerDpName(CareerDpName[i]);
						career.setAnnualSalary(AnnualSalary[i]);
						career.setCompanyName(CompanyName[i]);
						career.setPosition(Position[i]);
						userSpecManager.createSpecCareer(career, user.getUserNum());
						specCareers.add(career);
					}
					//userSpec.setSpecCareers(specCareers);
				}
				
				userSpec2 = userSpecManager.getUserSpec(user.getUserNum());
				request.setAttribute("userSpec", userSpec2);
				
				// 이동할 페이지를 결정.
				forward.setPath("user_spec_write.jsp");
				forward.setRedirect(false);
				
				
				
				// 이미지
				Connection conn = null;

				String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
				String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
				
				int UserNum = user.getUserNum();
				String image_name = UserNum + ".jpg";

				try {
					Class.forName(jdbc_driver);
					conn = DriverManager.getConnection(db_url,"eanby","eanby2012");
					/*create table mytable (bfile BLOB, cfile CLOB) 으로 테이블을 생성한 후에....
					 * 바이너리 데이터 컬럼에 바이너리 데이터를 저장한다.
					 */
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select USERPHOTO from SPEC_PHOTO where USERNUM=" + UserNum);
					
					while (rs.next()) {
						BLOB blob = (BLOB)rs.getBlob(1);

						InputStream instream = blob.getBinaryStream();
						FileOutputStream outstream = new FileOutputStream(pwd + image_name);

						int size = blob.getBufferSize();
						byte[] buffer = new byte[size];
						int length = -1;

						while ((length = instream.read(buffer)) != -1) {
							outstream.write(buffer, 0, length);
						}

						outstream.close();
						instream.close();
					}
					
					rs.close();
					stmt.close();
					conn.commit();
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}			
				
			} 
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("exception", e);
				forward.setPath("user_spec_write.jsp");
				forward.setRedirect(false);
			}

			return forward;
		}
}
