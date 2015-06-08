package userSpec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;

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

public class UserSpecManager {
	private static UserSpecManager userSpecManager = new UserSpecManager();
	private UserSpecDAO userSpecDAO;

	private UserSpecManager() {
		try {
			userSpecDAO = new UserSpecDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	public static UserSpecManager getInstance() {
		return userSpecManager;
	}

	
	/*public int create(UserSpecDTO userSpec, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		//if (userSpecDAO.getUserSpec(UserNum) != null) {
			val = userSpecDAO.create(userSpec, UserNum);
		//}
		//else {
			//val = userSpecDAO.create(userSpec, UserNum);
		//}
		
		return val;
	}
	
	*/

/*public int create(UserSpecDTO userSpec, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		//if (userSpecDAO.getUserSpec(UserNum) != null) {
			val = userSpecDAO.create(userSpec, UserNum);
		//}
		//else {
			//val = userSpecDAO.create(userSpec, UserNum);
		//}
		
		return val;
	}*/
	
	
	public int createSpecUserInfo(UserInfo userInfo, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecUserInfo(UserNum)) {
			val = userSpecDAO.createSpecUserInfo(userInfo, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecUserInfo(userInfo, UserNum);
		}		
		return val;
	}
	
	public int createSpecPhoto(Photo photo, int UserNum) throws SQLException, ExistedUserSpecException, ClassNotFoundException, IOException {		
		int val;
		String pwd = photo.getPwd();
		
		if (!userSpecDAO.getUserSpecPhoto(UserNum, pwd)) {
			val = userSpecDAO.createSpecPhoto(photo, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecPhoto(photo, UserNum);
		}
		
		return val;
	}
	
	public int createSpecLanguageLevel(LanguageLevel languageLevel, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecLanguageLevel(UserNum, languageLevel.getLanguageType())) {
			val = userSpecDAO.createSpecLanguageLevel(languageLevel, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecLanguageLevel(languageLevel, UserNum);
		}
		
		return val;
	}
	
	public int createSpecLanguageTest(LanguageTest languageTest, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecLanguageTest(UserNum, languageTest.getTestName())) {
			val = userSpecDAO.createSpecLanguageTest(languageTest, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecLanguageTest(languageTest, UserNum);
		}
		
		return val;
	}

	public int createSpecVolunteer(Volunteer volunteer, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecVolunteer(UserNum, volunteer.getVolunteerInstitution())) {
			val = userSpecDAO.createSpecVolunteer(volunteer, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecVolunteer(volunteer, UserNum);
		}
		
		return val;
	}
	
	

	public int createSpecLicence(Licence licence, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecLicence(UserNum, licence.getLicenceName())) {
			val = userSpecDAO.createSpecLicence(licence, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecLicence(licence, UserNum);
		}
		
		return val;
	}

	public int createSpecCareer(Career career, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecCareer(UserNum, career.getCompanyName())) {
			val = userSpecDAO.createSpecCareer(career, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecCareer(career, UserNum);
		}
		
		return val;
	}
	
	public int createSpecHighschool(Highschool highschool, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecHighschool(UserNum)) {
			val = userSpecDAO.createSpecHighschool(highschool, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecHighschool(highschool, UserNum);
		}
		
		return val;
	}

	public int createSpecUniversity(University university, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecUniversity(UserNum)) {
			val = userSpecDAO.createSpecUniversity(university, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecUniversity(university, UserNum);
		}
		
		return val;
	}
	
	public int createSpecForeignStudy(ForeignStudy foreignStudy, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecForeignStudy(UserNum, foreignStudy.getForeignNum())) {
			val = userSpecDAO.createSpecForeignStudy(foreignStudy, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecForeignStudy(foreignStudy, UserNum);
		}
		
		return val;
	}
	
	public int createSpecAward(Award award, int UserNum) throws SQLException, ExistedUserSpecException {
		
		int val;
		if (!userSpecDAO.getUserSpecAward(UserNum, award.getAwardname())) {
			val = userSpecDAO.createSpecAward(award, UserNum);
		}
		else {
			val = userSpecDAO.updateSpecAward(award, UserNum);
		}
		
		return val;
	}

	
/*	
 * public int update(UserSpecDTO userSpec, int UserNum) throws SQLException {
		return userSpecDAO.update(userSpec, UserNum);
	}	
*/
	public int remove(int UserNum) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpec(UserNum);
	}
	
	public int removeSpecPhoto(int UserNum, String LanguageType) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecPhoto(UserNum);
	}
	
	public int removeLanguageLevel(int UserNum, String LanguageType) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecLanguageLevel(UserNum, LanguageType);
	}
	
	public int removeLanguageTest(int UserNum, String TestName) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecLanguageTest(UserNum, TestName);
	}
	
	public int removeAward(int UserNum, String AwardName) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecAward(UserNum, AwardName);
	}

	
	public int removeForeignStudy(int UserNum, int FOREIGNNUM) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecForeignStudy(UserNum, FOREIGNNUM);
	}

	
	public int removeCareer(int UserNum, String COMPANYNAME) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecCareer(UserNum, COMPANYNAME);
	}

	
	public int removeVolunteer(int UserNum, String VOLUNTEERINSTITUTION) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecVolunteer(UserNum, VOLUNTEERINSTITUTION);
	}

	
	public int removeLicence(int UserNum, String LICENCENAME) throws SQLException { // 技记俊 历厘等 userNum 捞侩
		return userSpecDAO.removeSpecLicence(UserNum, LICENCENAME);
	}
	
	public UserSpecDTO getUserSpec(int UserNum) throws SQLException, UserSpecNotFoundException {
		
		UserSpecDTO userspec = null;
		
		if (userSpecDAO.getUserSpecUserInfo(UserNum)){
			userspec = userSpecDAO.getUserSpec(UserNum);
		}
	
		return userspec;
	}
	
	public boolean getUserPhoto(int UserNum, String pwd) throws SQLException, UserSpecNotFoundException, ClassNotFoundException, IOException {
		
		boolean photoExisted = false;
		
		if (userSpecDAO.getUserSpecPhoto(UserNum, pwd)){
			photoExisted = true;
		}
	
		return photoExisted;
	}
	
	/* private UserSpecDTO getUserSpecDTO(){
		try {
			return new UserSpecDTO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	} */
}
