package userApp;

import java.sql.SQLException;
import java.util.ArrayList;

import appForm.AppFormDTO;
import userApp.ExistedUserAppException;
import userApp.UserAppDTO;
import userApp.UserAppManager;
import userApp.UserAppNotFoundException;
import classes.*;

public class UserAppManager {
	private static UserAppManager userAppManager = new UserAppManager();
	private UserAppDAO userAppDAO; 

	private UserAppManager() {
		try {
			userAppDAO = new UserAppDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	public static UserAppManager getInstance() {
		return userAppManager;
	}
	
	public int createAppSelfIntroduction(SelfIntroduction self, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		int val;
		if (!userAppDAO.getAppSelfIntroduction(self.getSelfIntroNum(), UserNum, AppFormNum)) {
			val = userAppDAO.createAppSelfIntroduction(self, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppSelfIntroduction(self, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppUserInfo(UserInfo userInfo, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppUserInfo(UserNum, AppFormNum)) {
			val = userAppDAO.createAppUserInfo(userInfo, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppUserInfo(userInfo, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppLanguageLevel(LanguageLevel languageLevel, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppLanguageLevel(UserNum, languageLevel.getLanguageType(), AppFormNum)) {
			val = userAppDAO.createAppLanguageLevel(languageLevel, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppLanguageLevel(languageLevel, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppLanguageTest(LanguageTest languageTest, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppLanguageTest(UserNum, languageTest.getTestName(), AppFormNum)) {
			val = userAppDAO.createAppLanguageTest(languageTest, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppLanguageTest(languageTest, UserNum, AppFormNum);
		}
		
		return val;
	}

	public int createAppVolunteer(Volunteer volunteer, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppVolunteer(UserNum, volunteer.getVolunteerInstitution(), AppFormNum)) {
			val = userAppDAO.createAppVolunteer(volunteer, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppVolunteer(volunteer, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	

	public int createAppLicence(Licence licence, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppLicence(UserNum, licence.getLicenceName(), AppFormNum)) {
			val = userAppDAO.createAppLicence(licence, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppLicence(licence, UserNum, AppFormNum);
		}
		
		return val;
	}

	public int createAppCareer(Career career, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppCareer(UserNum, career.getCompanyName(), AppFormNum)) {
			val = userAppDAO.createAppCareer(career, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppCareer(career, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppHighschool(Highschool highschool, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppHighschool(UserNum, AppFormNum)) {
			val = userAppDAO.createAppHighschool(highschool, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppHighschool(highschool, UserNum, AppFormNum);
		}
		
		return val;
	}

	public int createAppUniversity(University university, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppUniversity(UserNum, AppFormNum)) {
			val = userAppDAO.createAppUniversity(university, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppUniversity(university, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppForeignStudy(ForeignStudy foreignStudy, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppForeignStudy(UserNum, foreignStudy.getForeignNum(), AppFormNum)) {
			val = userAppDAO.createAppForeignStudy(foreignStudy, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppForeignStudy(foreignStudy, UserNum, AppFormNum);
		}
		
		return val;
	}
	
	public int createAppAward(Award award, int UserNum, int AppFormNum) throws SQLException, ExistedUserAppException {
		
		int val;
		if (!userAppDAO.getUserAppAward(UserNum, award.getAwardname(), AppFormNum)) {
			val = userAppDAO.createAppAward(award, UserNum, AppFormNum);
		}
		else {
			val = userAppDAO.updateAppAward(award, UserNum, AppFormNum);
		}
		
		return val;
	}

	public int removeApp(int UserNum, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeApp(UserNum, AppFormNum);
	}
	
	public int removeAppLanguageLevel(int UserNum, String LanguageType, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppLanguageLevel(UserNum, LanguageType, AppFormNum);
	}
	
	public int removeAppLanguageTest(int UserNum, String TestName, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppLanguageTest(UserNum, TestName, AppFormNum);
	}
	
	public int removeAppAward(int UserNum, String AwardName, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppAward(UserNum, AwardName, AppFormNum);
	}

	
	public int removeAppForeignStudy(int UserNum, int FOREIGNNUM, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppForeignStudy(UserNum, FOREIGNNUM, AppFormNum);
	}

	
	public int removeAppCareer(int UserNum, String COMPANYNAME, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppCareer(UserNum, COMPANYNAME, AppFormNum);
	}

	
	public int removeAppVolunteer(int UserNum, String VOLUNTEERINSTITUTION, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppVolunteer(UserNum, VOLUNTEERINSTITUTION, AppFormNum);
	}

	
	public int removeAppLicence(int UserNum, String LICENCENAME, int AppFormNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userAppDAO.removeAppLicence(UserNum, LICENCENAME, AppFormNum);
	}
	
	public UserAppDTO getUserApp(int UserNum, int AppFormNum) throws SQLException {
		return userAppDAO.getUserApp(UserNum, AppFormNum);
	}
	
	public ArrayList<SelfIntroduction> getSelfIntrocduction(int UserNum, int AppFormNum) throws SQLException {
		return userAppDAO.getSelfIntrocduction(UserNum, AppFormNum);
	}
	
	public ArrayList<AppFormDTO> UserAppFormList(int userNum) throws Exception {

		ArrayList<AppFormDTO> userAppFormList = null;

		try {
			userAppFormList = userAppDAO.UserAppFormList(userNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAppFormList;

	}
	
	public ArrayList<AppFormDTO> searchUserAppFormList(int userNum,
			String keyword) throws Exception {
		ArrayList<AppFormDTO> userAppFormList = null;
		
		try {
			userAppFormList = userAppDAO.searchUserAppFormList(userNum, keyword);
		} 
		catch (SQLException e) {	
			e.printStackTrace();
		}
		return userAppFormList;
	}
}
