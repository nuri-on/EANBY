package userSpec;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import oracle.sql.BLOB;
import classes.*;

import javax.servlet.http.HttpServlet;

public class UserSpecDAO {
	
	private DataSource ds;
	
	public UserSpecDAO() throws Exception {
		
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	
	public int createSpecUserInfo(UserInfo info, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into SPEC_USERINFO(USERNUM, NAME1, NAME2, NAME3, BIRTHDAY, ADDRESS, EMAIL, PHONE1, PHONE2) values(?,?,?,?,?,?,?,?,?)"; 
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
	
	// 이미지 처리
		public int createSpecPhoto(Photo photo, int UserNum) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			int result = 0;

			try {
				conn = ds.getConnection();

				sql = "insert into SPEC_PHOTO(USERNUM, USERPHOTO) values(?,?)"; 
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, UserNum);

				if (photo.getPhotoPath() != null) {
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					FileInputStream fin;
					try {
						fin = new FileInputStream(photo.getPhotoPath());

						byte[] buf = new byte[1024];
						int read = 0;
						while((read=fin.read(buf,0,buf.length))!=-1){
							bout.write(buf,0,read);
						}
						fin.close();
						// byte배열이 완성되었다
						byte[] imageData = bout.toByteArray();

						// byte배열을 DB에 저장한다
						pstmt.setBytes(2, imageData);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
		
	
	public int createSpecHighschool(Highschool highschool, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "insert into SPEC_HIGHSCHOOL(USERNUM, HIGHSCHOOLNAME, ENTRANCEDAY, GRADUATEDAY) values(?,?,?,?)"; 
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
	
	public int createSpecUniversity(University university, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into SPEC_UNIVERSITY(USERNUM, UNIVERSITYNAME, ENTRANCEDAY, GRADUATEDAY, MAJOR, GRADE, CREDIT) values(?,?,?,?,?,?,?)"; 
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
	
	public int createSpecAward(Award award, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "insert into SPEC_AWARDS(USERNUM, AWARDDETAILS, AWARDINSTITUTION, AWARDDAY, AWARDNAME) values(?,?,?,?,?)";
	
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, award.getAwarddetails());
			pstmt.setString(3, award.getAwardinstitution());
			if (award.getAwardday() != null)
				pstmt.setDate(4, new java.sql.Date(award.getAwardday().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, award.getAwardname());
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
	
	public int createSpecCareer(Career career, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into SPEC_CAREER(USERNUM, CAREERTYPE, CAREERSTARTDAY, CAREERENDDAY, CAREERDPNAME, ANNUALSALARY, COMPANYNAME, POSITION) values(?,?,?,?,?,?,?,?)";
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
	
	public int createSpecForeignStudy(ForeignStudy foreignStudies, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into SPEC_FOREIGNSTUDIES(USERNUM, COUNTRYNAME, PURPOSEANDCONTENTS, STAYSTARTDAY, STAYENDDAY) values(?,?,?,?,?)";
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
	
	public int createSpecLanguageTest(LanguageTest languageTest, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into SPEC_LANGUAGETEST(USERNUM, TESTNAME, TESTSCORE, TESTLEVEL, TESTDAY) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, languageTest.getTestName());
			pstmt.setString(3, languageTest.getTestScore());
			pstmt.setString(4, languageTest.getTestLevel());
			if (languageTest.getTestDay() != null)
				pstmt.setDate(5, new java.sql.Date(languageTest.getTestDay().getTime()));
			else
				pstmt.setDate(5, null);
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
	
	
	public int createSpecLanguageLevel(LanguageLevel languagelevel, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into SPEC_LANGUAGELEVEL(USERNUM, LANGUAGETYPE , CONVERSATIONLEVEL, READINGLEVEL, WRITINGLEVEL) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, languagelevel.getLanguageType());
			pstmt.setString(3, languagelevel.getConversationLevel());
			pstmt.setString(4, languagelevel.getReadingLevel());
			pstmt.setString(5, languagelevel.getWritingLevel());
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
	
	public int createSpecLicence(Licence licence, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into SPEC_LICENCE(USERNUM, LICENCENAME , LICENCEINSTITUTION, LICENCESCORE , LICENCEDAY) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, licence.getLicenceName());
			pstmt.setString(3, licence.getLicenceInstitution());
			pstmt.setString(4, licence.getLicenceScore());
			
			if (licence.getLicenceday() != null)
				pstmt.setDate(5, new java.sql.Date(licence.getLicenceday().getTime()));
			else
				pstmt.setDate(5, null);

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
	
	
	public int createSpecVolunteer(Volunteer volunteer, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();

			sql = "insert into SPEC_VOLUNTEER(USERNUM, VOLUNTEERDETAILS, VOLUNTEERSTARTDAY, VOLUNTEERENDDAY, VOLUNTEERINSTITUTION) values(?,?,?,?,?)";

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
	

	public int updateSpecUserInfo(UserInfo userinfo, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update SPEC_USERINFO set NAME1=?, NAME2=?, NAME3=?, BIRTHDAY=?, ADDRESS=?, EMAIL=?, PHONE1=?, PHONE2=? where USERNUM=?"; 
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
	
	// 이미지 처리
	public int updateSpecPhoto(Photo photo, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;

		try {
			conn = ds.getConnection();
			sql = "update SPEC_PHOTO set USERPHOTO=? where USERNUM=?";
			pstmt = conn.prepareStatement(sql);			

			if (photo.getPhotoPath() != null) {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				FileInputStream fin;
				try {
					fin = new FileInputStream(photo.getPhotoPath());

					byte[] buf = new byte[1024];
					int read = 0;
					while((read=fin.read(buf,0,buf.length))!=-1){
						bout.write(buf,0,read);
					}
					fin.close();
					// byte배열이 완성되었다
					byte[] imageData = bout.toByteArray();

					// byte배열을 DB에 저장한다
					pstmt.setBytes(1, imageData);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt.setInt(2, UserNum);
				result = pstmt.executeUpdate();
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

	
	public int updateSpecHighschool(Highschool highschool, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update SPEC_HIGHSCHOOL set HIGHSCHOOLNAME=?, ENTRANCEDAY=?, GRADUATEDAY=? where USERNUM=?"; 
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
	
	public int updateSpecUniversity(University university, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update SPEC_UNIVERSITY set UNIVERSITYNAME=?, ENTRANCEDAY=?, GRADUATEDAY=?, MAJOR=?, GRADE=?, CREDIT=? where USERNUM=?"; 
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
	
	public int updateSpecAward(Award award, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update SPEC_AWARDS set AWARDDETAILS=?, AWARDINSTITUTION=?, AWARDDAY=? where USERNUM=? AND AWARDNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, award.getAwarddetails());
			pstmt.setString(2, award.getAwardinstitution());
			if (award.getAwardday() != null)
				pstmt.setDate(3, new java.sql.Date(award.getAwardday().getTime()));
			else
				pstmt.setDate(3, null);
			
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, award.getAwardname());
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
	
	
	public int updateSpecCareer(Career career, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			sql = "update SPEC_CAREER set CAREERTYPE=?, CAREERSTARTDAY=?, CAREERENDDAY=?, CAREERDPNAME=?, ANNUALSALARY=?, POSITION=? where USERNUM=? AND COMPANYNAME=?";
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
	
	public int updateSpecForeignStudy(ForeignStudy foreignStudy, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update SPEC_FOREIGNSTUDIES set COUNTRYNAME=?, PURPOSEANDCONTENTS=?, STAYSTARTDAY=?, STAYENDDAY=? where USERNUM=? AND FOREIGNNUM=?";
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
	
	public int updateSpecLanguageTest(LanguageTest languagetest, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update SPEC_LANGUAGETEST set TESTSCORE=?, TESTLEVEL=?, TESTDAY=? where USERNUM=? AND TESTNAME=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, languagetest.getTestScore());
			pstmt.setString(2, languagetest.getTestLevel());
			
			if (languagetest.getTestDay() != null)
				pstmt.setDate(3, new java.sql.Date(languagetest.getTestDay().getTime()));
			else
				pstmt.setDate(3, null);
			
			
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, languagetest.getTestName());
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
	
	public int updateSpecLanguageLevel(LanguageLevel languageLevel, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update SPEC_LANGUAGELEVEL set CONVERSATIONLEVEL=?, READINGLEVEL=?, WRITINGLEVEL=? where USERNUM=? AND LANGUAGETYPE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, languageLevel.getConversationLevel());
			pstmt.setString(2, languageLevel.getReadingLevel());
			pstmt.setString(3, languageLevel.getWritingLevel());
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, languageLevel.getLanguageType());
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
	
	public int updateSpecLicence(Licence licence, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update SPEC_LICENCE set LICENCEINSTITUTION=?, LICENCESCORE=?, LICENCEDAY=? where USERNUM=? AND LICENCENAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, licence.getLicenceInstitution());
			pstmt.setString(2, licence.getLicenceScore());
			
			if (licence.getLicenceday() != null)
				pstmt.setDate(3, new java.sql.Date(licence.getLicenceday().getTime()));
			else
				pstmt.setDate(3, null);
			
			pstmt.setInt(4, UserNum);
			pstmt.setString(5, licence.getLicenceName());
				
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
	
	public int updateSpecVolunteer(Volunteer volunteer, int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			sql = "update SPEC_VOLUNTEER set VOLUNTEERDETAILS=?, VOLUNTEERSTARTDAY=?, VOLUNTEERENDDAY=? where USERNUM=? AND VOLUNTEERINSTITUTION=?";
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
	
	
	public boolean getUserSpecUserInfo(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_USERINFO where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
	
	public boolean getUserSpecPhoto(int UserNum, String photoPath) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = false;
		String image_name = UserNum + ".jpg";

		try {
			conn = ds.getConnection();
			/*create table mytable (bfile BLOB, cfile CLOB) 으로 테이블을 생성한 후에....
			 * 바이너리 데이터 컬럼에 바이너리 데이터를 저장한다.
			 */
			sql = "select * from SPEC_PHOTO where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BLOB blob = (BLOB)rs.getBlob(2);

				InputStream instream = blob.getBinaryStream();
				FileOutputStream outstream = null;
				try {
					outstream = new FileOutputStream(photoPath + image_name);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int size = blob.getBufferSize();
				byte[] buffer = new byte[size];
				int length = -1;

				while ((length = instream.read(buffer)) != -1) {
					outstream.write(buffer, 0, length);
				}
				result = true;
				outstream.close();
				instream.close();
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
				conn.commit();
				conn.close();
				conn.close();
			}
		}
		return result;
	}
	
	public boolean getUserSpecHighschool(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_HIGHSCHOOL where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
	
	public boolean getUserSpecUniversity(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_UNIVERSITY where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
	
	
	public boolean getUserSpecAward(int UserNum, String awardname) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_AWARDS where USERNUM=? AND AWARDNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, awardname);
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
	
	public boolean getUserSpecCareer(int UserNum, String companyname) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_CAREER where USERNUM=? AND COMPANYNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, companyname);
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
	
	public boolean getUserSpecForeignStudy(int UserNum, int FOREIGNNUM) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_FOREIGNSTUDIES where USERNUM=? AND FOREIGNNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, FOREIGNNUM);
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
	
	public boolean getUserSpecLanguageTest(int UserNum, String TESTNAME) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_LANGUAGETEST where USERNUM=? AND TESTNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, TESTNAME);
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
	
	public boolean getUserSpecLanguageLevel(int UserNum, String LANGUAGETYPE) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_LANGUAGELEVEL where USERNUM=? AND LANGUAGETYPE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LANGUAGETYPE);
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
	
	public boolean getUserSpecLicence(int UserNum, String LICENCENAME) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_LICENCE where USERNUM=? AND LICENCENAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LICENCENAME);
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
	
	public boolean getUserSpecVolunteer(int UserNum, String VOLUNTEERINSTITUTION) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from SPEC_VOLUNTEER where USERNUM=? AND VOLUNTEERINSTITUTION=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, VOLUNTEERINSTITUTION);
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
	public int removeSpec(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_PHOTO where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_USERINFO  where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_HIGHSCHOOL where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_UNIVERSITY where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_AWARDS where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_CAREER where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_FOREIGNSTUDIES where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_LANGUAGETEST where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_LANGUAGELEVEL where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_LICENCE where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			result = pstmt.executeUpdate();
			if (result == 0) return result;
			
			sql = "delete from SPEC_VOLUNTEER where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
	
	public int removeSpecPhoto(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_PHOTO where USERNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
	
	public int removeSpecLanguageLevel(int UserNum, String LanguageType) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_LANGUAGELEVEL where USERNUM=? AND LANGUAGETYPE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LanguageType);
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
	
	public int removeSpecLanguageTest(int UserNum, String TestName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_LANGUAGETEST where USERNUM=? AND TESTNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, TestName);
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
	
	public int removeSpecAward(int UserNum, String AwardName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_AWARDS where USERNUM=? AND AWARDNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, AwardName);
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
	
	
	public int removeSpecForeignStudy(int UserNum, int FOREIGNNUM) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_FOREIGNSTUDIES where USERNUM=? AND FOREIGNNUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setInt(2, FOREIGNNUM);
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
	
	public int removeSpecCareer(int UserNum, String COMPANYNAME) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_CAREER where USERNUM=? AND COMPANYNAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, COMPANYNAME);
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
	
	public int removeSpecVolunteer(int UserNum, String VOLUNTEERINSTITUTION) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_VOLUNTEER where USERNUM=? AND VOLUNTEERINSTITUTION=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, VOLUNTEERINSTITUTION);
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
	
	public int removeSpecLicence(int UserNum, String LICENCENAME) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result;
		
		try {
			conn = ds.getConnection();
			
			sql = "delete from SPEC_LICENCE where USERNUM=? AND LICENCENAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			pstmt.setString(2, LICENCENAME);
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
	public UserSpecDTO getUserSpec(int UserNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		UserSpecDTO userSpec = null;
		String sql;
		
		try {
			conn = ds.getConnection();
			userSpec = new UserSpecDTO();
			
			/*sql = "select* from SPEC_PHOTO where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				userSpec.setSpecPhoto(rs.getBlob("USERPHOTO"));
			}*/
			
			
			sql = "select * from SPEC_HIGHSCHOOL where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				Highschool hs = new Highschool();
				hs.setUserNum(UserNum);
				hs.setEntanceDay(rs.getTimestamp("ENTRANCEDAY"));
				hs.setGraduateDay(rs.getTimestamp("GRADUATEDAY"));
				hs.setHighschoolName(rs.getString("HIGHSCHOOLNAME"));
				
				userSpec.setSpecHighschool(hs);				
			}
			
			sql = "select * from SPEC_UNIVERSITY where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				
				userSpec.setSpecUniversity(un);					
			}
			
			
			sql = "select * from SPEC_USERINFO where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				
				userSpec.setSpecUserInfo(ui);
			}
			
			
			sql = "select * from SPEC_LANGUAGELEVEL where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecLanguageLevels(datas);
			}
			
			
			sql = "select * from SPEC_LANGUAGETEST where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecLanguageTests(datas1);
			}
			
			
			sql = "select * from SPEC_LICENCE where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecLicences(datas2);
			}
			
			sql = "select * from SPEC_AWARDS where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecAwards(datas3);
			}
			
			
			sql = "select * from SPEC_FOREIGNSTUDIES where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecForeignStudies(datas4);
			}
			
			sql = "select * from SPEC_CAREER where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecCareers(datas5);
			}
			
			
			sql = "select * from SPEC_VOLUNTEER where USERNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, UserNum);
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
				userSpec.setSpecVolunteers(datas6);
			}
			
			return userSpec;
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
		
		return userSpec;
	}

}
