package userApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import appForm.AppFormDAO;
import appForm.AppFormDTO;
import classes.*;

public class UserAppDAO {
	
	private DataSource ds;
	
	public UserAppDAO() throws Exception {
		
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	public int createAppUserInfo(UserInfo info, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_USERINFO(USERNUM, NAME1, NAME2, NAME3, BIRTHDAY, ADDRESS, EMAIL, PHONE1, PHONE2, APPFORMNUM) values(?,?,?,?,?,?,?,?,?,?)";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, info.getName1());
			pstmt.setString(3, info.getName2());
			pstmt.setString(4, info.getName3());
			if (info.getBirthDay() != null)
				pstmt.setDate(5, new java.sql.Date(info.getBirthDay().getTime()));
			else
				pstmt.setDate(5, null);
			pstmt.setString(6, info.getAddress());
			pstmt.setString(7, info.getEmail());
			pstmt.setString(8, info.getPhone1());
			pstmt.setString(9, info.getPhone2());
			pstmt.setInt(10, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppHighschool(Highschool highschool, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_HIGHSCHOOL(USERNUM, HIGHSCHOOLNAME, ENTRANCEDAY, GRADUATEDAY, APPFORMNUM) values(?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, highschool.getHighschoolName());
			
			if (highschool.getEntanceDay() != null)
				pstmt.setDate(3, new java.sql.Date(highschool.getEntanceDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			if (highschool.getGraduateDay() != null)
				pstmt.setDate(4, new java.sql.Date(highschool.getGraduateDay().getTime()));
			else
				pstmt.setDate(4,null);
			
			pstmt.setInt(5, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppUniversity(University university, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_UNIVERSITY(USERNUM, UNIVERSITYNAME, ENTRANCEDAY, GRADUATEDAY, MAJOR, GRADE, CREDIT, APPFORMNUM) values(?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, university.getUniversityName());
			if (university.getEntranceDay() != null)
				pstmt.setDate(3, new java.sql.Date(university.getEntranceDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			if (university.getGraudateDay() != null)
				pstmt.setDate(4, new java.sql.Date(university.getGraudateDay().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, university.getMajor());
			pstmt.setString(6, university.getGrade());
			pstmt.setString(7, university.getCredit());
			pstmt.setInt(8, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppAward(Award award, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "insert into USERAPP_AWARDS(USERNUM, AWARDDETAILS, AWARDINSTITUTION, AWARDDAY, AWARDNAME, APPFORMNUM) values(?,?,?,?,?,?)";
	
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, award.getAwarddetails());
			pstmt.setString(3, award.getAwardinstitution());
			if (award.getAwardday() != null)
				pstmt.setDate(4, new java.sql.Date(award.getAwardday().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, award.getAwardname());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	
	public int createAppCareer(Career career, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_CAREER(USERNUM, CAREERTYPE, CAREERSTARTDAY, CAREERENDDAY, CAREERDPNAME, ANNUALSALARY, COMPANYNAME, POSITION, APPFORMNUM) values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, career.getCareerType());
			if (career.getCareerStartDay() != null)
				pstmt.setDate(3, new java.sql.Date(career.getCareerStartDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			if (career.getCareerEndDay() != null)
				pstmt.setDate(4, new java.sql.Date(career.getCareerEndDay().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, career.getCareerDpName());
			pstmt.setString(6, career.getAnnualSalary());
			pstmt.setString(7, career.getCompanyName());
			pstmt.setString(8, career.getPosition());
			pstmt.setInt(9, AppFormNum);
			result = pstmt.executeUpdate();

		return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppForeignStudy(ForeignStudy foreignStudies, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into USERAPP_FOREIGNSTUDIES(USERNUM, COUNTRYNAME, PURPOSEANDCONTENTS, STAYSTARTDAY, STAYENDDAY, APPFORMNUM) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, foreignStudies.getCountryName());
			pstmt.setString(3, foreignStudies.getPurposeAndContents());
			if (foreignStudies.getStayStartDay() != null)
				pstmt.setDate(4, new java.sql.Date(foreignStudies.getStayStartDay().getTime()));
			else
				pstmt.setDate(4, null);
			
			if (foreignStudies.getStayEndDay() != null)
				pstmt.setDate(5, new java.sql.Date(foreignStudies.getStayEndDay().getTime()));
			else
				pstmt.setDate(5, null);
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();

			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppLanguageTest(LanguageTest languageTest, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_LANGUAGETEST(USERNUM, TESTNAME, TESTSCORE, TESTLEVEL, TESTDAY, APPFORMNUM) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, languageTest.getTestName());
			pstmt.setString(3, languageTest.getTestScore());
			pstmt.setString(4, languageTest.getTestLevel());
			if (languageTest.getTestDay() != null)
				pstmt.setDate(5, new java.sql.Date(languageTest.getTestDay().getTime()));
			else
				pstmt.setDate(5, null);
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	
	public int createAppLanguageLevel(LanguageLevel languagelevel, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into USERAPP_LANGUAGELEVEL(USERNUM, LANGUAGETYPE , CONVERSATIONLEVEL, READINGLEVEL, WRITINGLEVEL, APPFORMNUM) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, languagelevel.getLanguageType());
			pstmt.setString(3, languagelevel.getConversationLevel());
			pstmt.setString(4, languagelevel.getReadingLevel());
			pstmt.setString(5, languagelevel.getWritingLevel());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int createAppLicence(Licence licence, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into USERAPP_LICENCE(USERNUM, LICENCENAME , LICENCEINSTITUTION, LICENCESCORE , LICENCEDAY, APPFORMNUM) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, licence.getLicenceName());
			pstmt.setString(3, licence.getLicenceInstitution());
			pstmt.setString(4, licence.getLicenceScore());
			if (licence.getLicenceday() != null)
				pstmt.setDate(5, new java.sql.Date(licence.getLicenceday().getTime()));
			else
				pstmt.setDate(5, null);
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}	
	
	
	public int createAppVolunteer(Volunteer volunteer, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into USERAPP_VOLUNTEER(USERNUM, VOLUNTEERDETAILS, VOLUNTEERSTARTDAY, VOLUNTEERENDDAY, VOLUNTEERINSTITUTION, APPFORMNUM) values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, volunteer.getVolunteerDetails());
			
			if (volunteer.getVolunteerStartDay() != null)
				pstmt.setDate(3, new java.sql.Date(volunteer.getVolunteerStartDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			if (volunteer.getVolunteerEndDay() != null)
				pstmt.setDate(4, new java.sql.Date(volunteer.getVolunteerEndDay().getTime()));
			else
				pstmt.setDate(4, null);
			
			pstmt.setString(5, volunteer.getVolunteerInstitution());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();

			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}	
	
	public int createAppSelfIntroduction(SelfIntroduction self, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into USERAPP_SELFINTRODUCTION(USERNUM, APPFORMNUM, SELFINTRONUM, SELFINTROCONTENTS) values(?,?,?,?)";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			pstmt.setInt(3, self.getSelfIntroNum());
			pstmt.setString(4, self.getSelfIntroContents());
			
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppSelfIntroduction(SelfIntroduction self, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "update USERAPP_SELFINTRODUCTION set SELFINTROCONTENTS=? WHERE USERNUM=? AND APPFORMNUM=? AND SELFINTRONUM=?";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, self.getSelfIntroContents());
			pstmt.setInt(2, UserNum);
			pstmt.setInt(3, AppFormNum);
			pstmt.setInt(4, self.getSelfIntroNum());
			result = pstmt.executeUpdate();
	
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppUserInfo(UserInfo userinfo, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update USERAPP_USERINFO set NAME1=?, NAME2=?, NAME3=?, BIRTHDAY=?, ADDRESS=?, EMAIL=?, PHONE1=?, PHONE2=? where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userinfo.getName1());
			pstmt.setString(2, userinfo.getName2());
			pstmt.setString(3, userinfo.getName3());
			if (userinfo.getBirthDay() != null)
				pstmt.setDate(4, new java.sql.Date(userinfo.getBirthDay().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, userinfo.getAddress());
			pstmt.setString(6, userinfo.getEmail());
			pstmt.setString(7, userinfo.getPhone1());
			pstmt.setString(8, userinfo.getPhone2());
			pstmt.setInt(9, UserNum);
			pstmt.setInt(10, AppFormNum);
			result = pstmt.executeUpdate();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppHighschool(Highschool highschool, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update USERAPP_HIGHSCHOOL set HIGHSCHOOLNAME=?, ENTRANCEDAY=?, GRADUATEDAY=? where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, highschool.getHighschoolName());
			if (highschool.getEntanceDay() != null)
				pstmt.setDate(2, new java.sql.Date(highschool.getEntanceDay().getTime()));
			else
				pstmt.setDate(2, null);
			
			if (highschool.getGraduateDay() != null)
				pstmt.setDate(3, new java.sql.Date(highschool.getGraduateDay().getTime()));
			else
				pstmt.setDate(3,null);
			pstmt.setInt(4, UserNum);
			pstmt.setInt(5, AppFormNum);
			result = pstmt.executeUpdate();

			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppUniversity(University university, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update USERAPP_UNIVERSITY set UNIVERSITYNAME=?, ENTRANCEDAY=?, GRADUATEDAY=?, MAJOR=?, GRADE=?, CREDIT=? where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, university.getUniversityName());
			if (university.getEntranceDay() != null)
				pstmt.setDate(2, new java.sql.Date(university.getEntranceDay().getTime()));
			else
				pstmt.setDate(2, null);
			
			if (university.getGraudateDay() != null)
				pstmt.setDate(3, new java.sql.Date(university.getGraudateDay().getTime()));
			else
				pstmt.setDate(3, null);
			pstmt.setString(4, university.getMajor());
			pstmt.setString(5, university.getGrade());
			pstmt.setString(6, university.getCredit());
			pstmt.setInt(7, UserNum);
			pstmt.setInt(8, AppFormNum);
			result = pstmt.executeUpdate();
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppAward(Award award, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update USERAPP_AWARDS set AWARDDETAILS=?, AWARDINSTITUTION=?, AWARDDAY=? where USERNUM=? AND AWARDNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, award.getAwarddetails());
			pstmt.setString(2, award.getAwardinstitution());
			if (award.getAwardday() != null)
				pstmt.setDate(3, new java.sql.Date(award.getAwardday().getTime()));
			else
				pstmt.setDate(3, null);
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, award.getAwardname());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
				
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	
	public int updateAppCareer(Career career, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update USERAPP_CAREER set CAREERTYPE=?, CAREERSTARTDAY=?, CAREERENDDAY=?, CAREERDPNAME=?, ANNUALSALARY=?, POSITION=? where USERNUM=? AND COMPANYNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, career.getCareerType());
			if (career.getCareerStartDay() != null)
				pstmt.setDate(2, new java.sql.Date(career.getCareerStartDay().getTime()));
			else
				pstmt.setDate(2, null);
			
			if (career.getCareerEndDay() != null)
				pstmt.setDate(3, new java.sql.Date(career.getCareerEndDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			pstmt.setString(4, career.getCareerDpName());
			pstmt.setString(5, career.getAnnualSalary());
			pstmt.setString(6, career.getPosition());
			pstmt.setInt(7, UserNum);
			pstmt.setString(8, career.getCompanyName());
			pstmt.setInt(9, AppFormNum);
			result = pstmt.executeUpdate();
		
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppForeignStudy(ForeignStudy foreignStudy, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update USERAPP_FOREIGNSTUDIES set COUNTRYNAME=?, PURPOSEANDCONTENTS=?, STAYSTARTDAY=?, STAYENDDAY=? where USERNUM=? AND FOREIGNNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, foreignStudy.getCountryName());
			pstmt.setString(2, foreignStudy.getPurposeAndContents());
			if (foreignStudy.getStayStartDay() != null)
				pstmt.setDate(3, new java.sql.Date(foreignStudy.getStayStartDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			if (foreignStudy.getStayEndDay() != null)
				pstmt.setDate(4, new java.sql.Date(foreignStudy.getStayEndDay().getTime()));
			else
				pstmt.setDate(4, null);
			
			pstmt.setInt(5, UserNum);
			pstmt.setInt(6, foreignStudy.getForeignNum());
			pstmt.setInt(7, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppLanguageTest(LanguageTest languagetest, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update USERAPP_LANGUAGETEST set TESTSCORE=?, TESTLEVEL=?, TESTDAY=? where USERNUM=? AND TESTNAME=? AND APPFORMNUM=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, languagetest.getTestScore());
			pstmt.setString(2, languagetest.getTestLevel());
			if (languagetest.getTestDay() != null)
				pstmt.setDate(3, new java.sql.Date(languagetest.getTestDay().getTime()));
			else
				pstmt.setDate(3, null);
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, languagetest.getTestName());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppLanguageLevel(LanguageLevel languageLevel, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update USERAPP_LANGUAGELEVEL set CONVERSATIONLEVEL=?, READINGLEVEL=?, WRITINGLEVEL=? where USERNUM=? AND LANGUAGETYPE=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, languageLevel.getConversationLevel());
			pstmt.setString(2, languageLevel.getReadingLevel());
			pstmt.setString(3, languageLevel.getWritingLevel());
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, languageLevel.getLanguageType());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppLicence(Licence licence, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update USERAPP_LICENCE set LICENCEINSTITUTION=?, LICENCESCORE=?, LICENCEDAY=? where USERNUM=? AND LICENCENAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, licence.getLicenceInstitution());
			pstmt.setString(2, licence.getLicenceScore());
			if (licence.getLicenceday() != null)
				pstmt.setDate(3, new java.sql.Date(licence.getLicenceday().getTime()));
			else
				pstmt.setDate(3, null);
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, licence.getLicenceName());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public int updateAppVolunteer(Volunteer volunteer, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update USERAPP_VOLUNTEER set VOLUNTEERDETAILS=?, VOLUNTEERSTARTDAY=?, VOLUNTEERENDDAY=? where USERNUM=? AND VOLUNTEERINSTITUTION=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, volunteer.getVolunteerDetails());
			
			if (volunteer.getVolunteerStartDay() != null)
				pstmt.setDate(2, new java.sql.Date(volunteer.getVolunteerStartDay().getTime()));
			else
				pstmt.setDate(2, null);
			
			if (volunteer.getVolunteerEndDay() != null)
				pstmt.setDate(3, new java.sql.Date(volunteer.getVolunteerEndDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, volunteer.getVolunteerInstitution());
			pstmt.setInt(6, AppFormNum);
			result = pstmt.executeUpdate();
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppUserInfo(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_USERINFO where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppHighschool(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_HIGHSCHOOL where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppUniversity(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_UNIVERSITY where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	
	public boolean getUserAppAward(int UserNum, String awardname, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_AWARDS where USERNUM=? AND AWARDNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, awardname);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppCareer(int UserNum, String companyname, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_CAREER where USERNUM=? AND COMPANYNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, companyname);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppForeignStudy(int UserNum, int FOREIGNNUM, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_FOREIGNSTUDIES where USERNUM=? AND FOREIGNNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, FOREIGNNUM);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppLanguageTest(int UserNum, String TESTNAME, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_LANGUAGETEST where USERNUM=? AND TESTNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, TESTNAME);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppLanguageLevel(int UserNum, String LANGUAGETYPE, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_LANGUAGELEVEL where USERNUM=? AND LANGUAGETYPE=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LANGUAGETYPE);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppLicence(int UserNum, String LICENCENAME, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_LICENCE where USERNUM=? AND LICENCENAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LICENCENAME);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	public boolean getUserAppVolunteer(int UserNum, String VOLUNTEERINSTITUTION, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_VOLUNTEER where USERNUM=? AND VOLUNTEERINSTITUTION=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, VOLUNTEERINSTITUTION);
			pstmt.setInt(3, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	public boolean getAppSelfIntroduction(int SelfIntroNum, int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_SELFINTRODUCTION WHERE USERNUM=? AND APPFORMNUM=? AND SELFINTRONUM=?";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			pstmt.setInt(3, SelfIntroNum);
			rs = pstmt.executeQuery();
			
			if ( rs.next()) 
				return result;
			else {
				result = false;
				return result;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ) {
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
			
		}
		return result;
	}
	
	@SuppressWarnings("resource")
	public int removeApp(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_PHOTO where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_USERINFO  where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_HIGHSCHOOL where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_UNIVERSITY where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_AWARDS where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_CAREER where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_FOREIGNSTUDIES where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_LANGUAGETEST where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_LANGUAGELEVEL where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_LICENCE where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from USERAPP_VOLUNTEER where USERNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			return result;

		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}		
	}
	
	public int removeAppLanguageLevel(int UserNum, String LanguageType, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_LANGUAGELEVEL where USERNUM=? AND LANGUAGETYPE=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LanguageType);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		
	}
	
	public int removeAppLanguageTest(int UserNum, String TestName, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_LANGUAGETEST where USERNUM=? AND TESTNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, TestName);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		
	}
	
	public int removeAppAward(int UserNum, String AwardName, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_AWARDS where USERNUM=? AND AWARDNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, AwardName);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		
	}
	
	
	public int removeAppForeignStudy(int UserNum, int FOREIGNNUM, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_FOREIGNSTUDIES where USERNUM=? AND FOREIGNNUM=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, FOREIGNNUM);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		
	}
	
	public int removeAppCareer(int UserNum, String COMPANYNAME, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_CAREER where USERNUM=? AND COMPANYNAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, COMPANYNAME);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	}
	
	public int removeAppVolunteer(int UserNum, String VOLUNTEERINSTITUTION, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_VOLUNTEER where USERNUM=? AND VOLUNTEERINSTITUTION=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, VOLUNTEERINSTITUTION);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	}
	
	public int removeAppLicence(int UserNum, String LICENCENAME, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from USERAPP_LICENCE where USERNUM=? AND LICENCENAME=? AND APPFORMNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LICENCENAME);
			pstmt.setInt(3, AppFormNum);
			result = pstmt.executeUpdate();
			
			return result;
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	}
	
	@SuppressWarnings("resource")
	public UserAppDTO getUserApp(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		UserAppDTO userApp = null;
		String sql;
		
		try {
			conn = ds.getConnection();
			
			/*sql = "select* from SPEC_PHOTO where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				userApp.setSpecPhoto(rs.getBlob("USERPHOTO"));
			}*/
			
			
			sql = "select * from USERAPP_HIGHSCHOOL where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				userApp = new UserAppDTO();
				Highschool hs = new Highschool();
				hs.setUserNum(UserNum);
				hs.setEntanceDay(rs.getTimestamp("ENTRANCEDAY"));
				hs.setGraduateDay(rs.getTimestamp("GRADUATEDAY"));
				hs.setHighschoolName(rs.getString("HIGHSCHOOLNAME"));
				
				userApp.setAppHighschool(hs);				
			}
			else {
				return userApp;
			}
			
			sql = "select * from USERAPP_UNIVERSITY where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				University un = new University();
				un.setUserNum(UserNum);
				un.setEntranceDay(rs.getTimestamp("ENTRANCEDAY"));
				un.setGraudateDay(rs.getTimestamp("GRADUATEDAY"));
				un.setUniversityName(rs.getString("UNIVERSITYNAME"));
				un.setMajor(rs.getString("MAJOR"));
				un.setGrade(rs.getString("GRADE"));
				un.setCredit(rs.getString("CREDIT"));
				
				userApp.setAppUniversity(un);					
			}
			
			
			sql = "select * from USERAPP_USERINFO where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				UserInfo ui = new UserInfo();
				
				ui.setUserNum(UserNum);
				ui.setName1(rs.getString("NAME1"));
				ui.setName2(rs.getString("NAME2"));
				ui.setName3(rs.getString("NAME3"));
				ui.setBirthDay(rs.getTimestamp("BIRTHDAY"));
				ui.setAddress(rs.getString("ADDRESS"));
				ui.setEmail(rs.getString("EMAIL"));
				ui.setPhone1(rs.getString("PHONE1"));
				ui.setPhone2(rs.getString("PHONE2"));
				
				userApp.setAppUserInfo(ui);
			}
			
			
			sql = "select * from USERAPP_LANGUAGELEVEL where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<LanguageLevel> datas = new ArrayList<LanguageLevel>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 LanguageLevel ll = new LanguageLevel();
					 ll.setUserNum(UserNum);
					 ll.setLanguageType(rs.getString("LANGUAGETYPE"));
					 ll.setConversationLevel(rs.getString("CONVERSATIONLEVEL"));
					 ll.setReadingLevel(rs.getString("READINGLEVEL"));
					 ll.setWritingLevel(rs.getString("WRITINGLEVEL"));
					 
					 datas.add(ll);
				 }
				 userApp.setAppLanguageLevels(datas);
			}
			
			
			sql = "select * from USERAPP_LANGUAGETEST where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<LanguageTest> datas1 = new ArrayList<LanguageTest>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 LanguageTest lt = new LanguageTest();
					 lt.setUserNum(UserNum);
					 lt.setTestName(rs.getString("TESTNAME"));
					 lt.setTestScore(rs.getString("TESTSCORE"));
					 lt.setTestLevel(rs.getString("TESTLEVEL"));
					 lt.setTestDay(rs.getTimestamp("TESTDAY"));
					 
					 datas1.add(lt);
				 }
				 userApp.setAppLanguageTests(datas1);
			}
			
			
			sql = "select * from USERAPP_LICENCE where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<Licence> datas2 = new ArrayList<Licence>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 Licence lc = new Licence();
					 lc.setUserNum(UserNum);
					 lc.setLicenceName(rs.getString("LICENCENAME"));
					 lc.setLicenceInstitution(rs.getString("LICENCEINSTITUTION"));
					 lc.setLicenceScore(rs.getString("LICENCESCORE"));
					 lc.setLicenceday(rs.getTimestamp("LICENCEDAY"));
					 
					 datas2.add(lc);
				 }
				 userApp.setAppLicences(datas2);
			}
			
			sql = "select * from USERAPP_AWARDS where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<Award> datas3 = new ArrayList<Award>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 Award aw = new Award();
					 aw.setUserNum(UserNum);
					 aw.setAwarddetails(rs.getString("AWARDDETAILS"));
					 aw.setAwardinstitution(rs.getString("AWARDINSTITUTION"));
					 aw.setAwardday(rs.getDate("AWARDDAY"));
					 aw.setAwardname(rs.getString("AWARDNAME"));
					 
					 datas3.add(aw);
				 }
				 userApp.setAppAwards(datas3);
			}
			
			/*
			sql = "select * from USERAPP_FOREIGNSTUDIES where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<ForeignStudy> datas4 = new ArrayList<ForeignStudy>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 ForeignStudy fs = new ForeignStudy();
					 fs.setUserNum(UserNum);
					 fs.setCountryName(rs.getString("COUNTRYNAME"));
					 fs.setPurposeAndContents(rs.getString("PURPOSEANDCONTENTS"));
					 fs.setStayStartDay(rs.getTimestamp("STAYSTARTDAY"));
					 fs.setStayEndDay(rs.getTimestamp("STAYENDDAY"));
					 fs.setForeignNum(rs.getInt("FOREIGNNUM"));
					 
					 datas4.add(fs);
				 }
				 userApp.setAppForeignStudies(datas4);
			}
			*/
			
			sql = "select * from USERAPP_CAREER where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<Career> datas5 = new ArrayList<Career>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 Career ca = new Career();
					 ca.setUserNum(UserNum);
					 ca.setCareerType(rs.getString("CAREERTYPE"));
					 ca.setCareerStartDay(rs.getTimestamp("CAREERSTARTDAY"));
					 ca.setCareerEndDay(rs.getTimestamp("CAREERENDDAY"));
					 ca.setCareerDpName(rs.getString("CAREERDPNAME"));
					 ca.setAnnualSalary(rs.getString("ANNUALSALARY"));
					 ca.setCompanyName(rs.getString("COMPANYNAME"));
					 ca.setPosition(rs.getString("POSITION"));
					 datas5.add(ca);
				 }
				 userApp.setAppCareers(datas5);
			}
			
			
			sql = "select * from USERAPP_VOLUNTEER where USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			ArrayList<Volunteer> datas6 = new ArrayList<Volunteer>();
			rs = pstmt.executeQuery();
			if (rs != null) {
				 while (rs.next()) {
					 Volunteer vo = new Volunteer();
					 vo.setUserNum(UserNum);
					 vo.setVolunteerDetails(rs.getString("VOLUNTEERDETAILS"));
					 vo.setVolunteerStartDay(rs.getTimestamp("VOLUNTEERSTARTDAY"));
					 vo.setVolunteerEndDay(rs.getTimestamp("VOLUNTEERENDDAY"));
					 vo.setVolunteerInstitution(rs.getString("VOLUNTEERINSTITUTION"));
					 datas6.add(vo);
				 }
				 userApp.setAppVolunteers(datas6);
			}
			
			return userApp;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		
		return userApp;
	}
	public ArrayList<SelfIntroduction> getSelfIntrocduction(int UserNum, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		String sql;
		ArrayList<SelfIntroduction> SelfIntroductions = null;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from USERAPP_SELFINTRODUCTION WHERE USERNUM=? AND APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, AppFormNum);
			rs = pstmt.executeQuery();
			if (rs != null) {
				SelfIntroductions = new ArrayList<SelfIntroduction>();
				while (rs.next()) {
					SelfIntroduction self = new SelfIntroduction();
					self.setSelfIntroContents(rs.getString("SELFINTROCONTENTS"));
					self.setSelfIntroNum(rs.getInt("SELFINTRONUM"));
					
					SelfIntroductions.add(self);
				}
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
		return SelfIntroductions;
		
		
	}

	public ArrayList<AppFormDTO> UserAppFormList(int UserNum) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		ArrayList<AppFormDTO> UserAppFormList = null;
		String sql;
		AppFormDTO userAppForm;
		AppFormDAO UserAppFormDAO = new AppFormDAO();
		try {
			conn = ds.getConnection();
			sql = "select APPFORMNUM from USERAPP_USERINFO WHERE USERNUM = " + UserNum;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				UserAppFormList = new ArrayList<AppFormDTO>();
				while(rs.next()) {
					userAppForm = UserAppFormDAO.getAppForm(rs.getInt("APPFORMNUM"));
					UserAppFormList.add(userAppForm);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	
		
		return UserAppFormList;
	}
	
	public ArrayList<AppFormDTO> searchUserAppFormList(int userNum,
			String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		ArrayList<AppFormDTO> UserAppFormList = null;
		String sql;
		AppFormDTO userAppForm;
		AppFormDAO UserAppFormDAO = new AppFormDAO();
		try {
			conn = ds.getConnection();
			sql = "select APPFORMNUM from USERAPP_USERINFO WHERE USERNUM = " + userNum;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				UserAppFormList = new ArrayList<AppFormDTO>();
				while(rs.next()) {
					userAppForm = UserAppFormDAO.getSearchAppForm(rs.getInt("APPFORMNUM"), keyword);
					if (userAppForm != null)
						UserAppFormList.add(userAppForm);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	
		
		return UserAppFormList;
	}
}
