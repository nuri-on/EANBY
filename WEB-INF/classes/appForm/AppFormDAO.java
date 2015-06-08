package appForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;








import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import userSpec.UserSpecDTO;
import classes.AppFormUserInfo;
import classes.Award;
import classes.Highschool;
import classes.SelfIntroduction;


public class AppFormDAO {
	private DataSource ds;
	
	public AppFormDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	


@SuppressWarnings("resource")
public int createAppFormRegister(int managernum) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		
		int result = -1;
		
		try {
			conn = ds.getConnection();
		
			sql = "insert into APPFORM_REGISTER(APPFORMREGISTERDAY, MANAGERNUM) values(?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
			pstmt.setInt(2, managernum);
			pstmt.executeUpdate();
			
			sql = "select TEST6.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			return result-1;
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
	
	public int createAppForm(AppFormDTO appForm, int AppFormNum) throws SQLException {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			sql = "insert into APPFORM(APPFORMNUM, UNIVERSITY, CAREER, LANGUAGELEVEL, LANGUAGETEST, LICENCE, AWARD, COMPANYNAME, REGISTERNAME, REGISTERSTARTDAY, REGISTERENDDAY, PREFERENCE, DETAIL, HIGHSCHOOL, VOLUNTEER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AppFormNum);
			pstmt.setString(2, appForm.getAppFormUniversity());
			pstmt.setInt(3, appForm.getAppFormCareers());
			pstmt.setInt(4, appForm.getAppFormLanguageLevel());
			pstmt.setInt(5, appForm.getAppFormLanguageTest());
			pstmt.setInt(6, appForm.getAppFormLicences());
			pstmt.setInt(7, appForm.getAppFormAwards());
			pstmt.setString(8, appForm.getAppFormCompany());
			pstmt.setString(9, appForm.getAppFormRegisterName());
			pstmt.setDate(10, new java.sql.Date(appForm.getAppFormStartDate().getTime()));
			pstmt.setDate(11, new java.sql.Date(appForm.getAppFormEndDate().getTime()));
			pstmt.setString(12, appForm.getPreference());
			pstmt.setString(13, appForm.getDetail());
			pstmt.setString(14, appForm.getAppFormHighschool());
			pstmt.setInt(15, appForm.getAppFormVolunteers());
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
		
	public int createAppFormInfo(AppFormDTO appForm, int AppFormNum) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			sql = "insert into APPFORM_INFO(NAME1, NAME2, NAME3, BIRTHDAY, ADDRESS, EMAIL, PHONE2, PHONE1, APPFORMNUM) values(?,?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, appForm.getAppFormUserInfo().getName1());
			pstmt.setString(2, appForm.getAppFormUserInfo().getName2());
			pstmt.setString(3, appForm.getAppFormUserInfo().getName3());
			pstmt.setString(4, appForm.getAppFormUserInfo().getBirthday());
			pstmt.setString(5, appForm.getAppFormUserInfo().getAddress());
			pstmt.setString(6, appForm.getAppFormUserInfo().getEmail());
			pstmt.setString(7, appForm.getAppFormUserInfo().getPhone2());
			pstmt.setString(8, appForm.getAppFormUserInfo().getPhone1());
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
	
	public int createAppFormSelfIntro(ArrayList<SelfIntroduction> SelfIntroductions, int AppFormNum) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			for (SelfIntroduction self : SelfIntroductions) {
				sql = "insert into APPFORM_SELFINTRO(APPFORMNUM, ITEMNAME, INTROSIZE, SELFINTRONUM) values(?,?,?,?)"; 
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, AppFormNum);
				pstmt.setString(2, self.getItemName());
				pstmt.setInt(3, self.getIntroSize());
				pstmt.setInt(4, self.getSelfIntroNum());
				result = pstmt.executeUpdate();
			}
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
	
	public int updateAppFormSelfIntro(SelfIntroduction selfintroduction, int AppFormNum) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			sql = "update APPFORM_SELFINTRO set ITEMNAME=?, INTROSIZE=?, SELFINTRONUM=? WHERE APPFORMNUM=?" ; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, selfintroduction.getItemName());
			pstmt.setInt(2, selfintroduction.getIntroSize());
			pstmt.setInt(3, selfintroduction.getSelfIntroNum());
			pstmt.setInt(4, AppFormNum);
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
	
	public int updateAppFormInfo(AppFormUserInfo appFormUserInfo, int AppFormNum) throws SQLException  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			sql = "update APPFORM_INFO set NAME1=?, NAME2=?, NAME3=?, BIRTHDAY=?, ADDRESS=?, EMAIL=?, PHONE2=?, PHONE1=? WHERE APPFORMNUM=?" ; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, appFormUserInfo.getName1());
			pstmt.setString(2, appFormUserInfo.getName2());
			pstmt.setString(3, appFormUserInfo.getName3());
			pstmt.setString(4, appFormUserInfo.getBirthday());
			pstmt.setString(5, appFormUserInfo.getAddress());
			pstmt.setString(6, appFormUserInfo.getEmail());
			pstmt.setString(7, appFormUserInfo.getPhone2());
			pstmt.setString(8, appFormUserInfo.getPhone1());
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
	
	public int updateAppForm(AppFormDTO appForm, int AppFormNum) throws SQLException {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		int result = 0;
			
		try {
			conn = ds.getConnection();
			
			sql = "update APPFORM set UNIVERSITY=?, CAREER=?, LANGUAGELEVEL=?, LANGUAGETEST=?, LICENCE=?, AWARD=?, COMPANYNAME=?, REGISTERNAME=?, REGISTERSTARTDAY=?, REGISTERENDDAY=?, PREFERENCE=?, DETAIL=?, HIGHSCHOOL=?, VOLUNTEER=? where APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, appForm.getAppFormUniversity());
			pstmt.setInt(2, appForm.getAppFormCareers());
			pstmt.setInt(3, appForm.getAppFormLanguageLevel());
			pstmt.setInt(4, appForm.getAppFormLanguageTest());
			pstmt.setInt(5, appForm.getAppFormLicences());
			pstmt.setInt(6, appForm.getAppFormAwards());
			pstmt.setString(7, appForm.getAppFormCompany());
			pstmt.setString(8, appForm.getAppFormRegisterName());
			pstmt.setDate(9, new java.sql.Date(appForm.getAppFormStartDate().getTime()));
			pstmt.setDate(10, new java.sql.Date(appForm.getAppFormEndDate().getTime()));
			pstmt.setString(11, appForm.getPreference());
			pstmt.setString(12, appForm.getDetail());
			pstmt.setString(13, appForm.getAppFormHighschool());
			pstmt.setInt(14, appForm.getAppFormVolunteers());
			pstmt.setInt(15, AppFormNum);
			
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
	
	/*public int remove(String userId) throws SQLException {
		
	}
	*/
	
	@SuppressWarnings("resource")
	public AppFormDTO getAppForm(int AppFormNum) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		AppFormDTO userAppForm = null;
		String sql;
		
		try {
			conn = ds.getConnection();
			
			sql = "select * from APPFORM where APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AppFormNum);
			
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				userAppForm = new AppFormDTO();
				userAppForm.setAppFormUniversity(rs.getString("UNIVERSITY"));
				userAppForm.setAppFormCareers(rs.getInt("CAREER"));
				userAppForm.setAppFormLanguageLevel(rs.getInt("LANGUAGELEVEL"));
				userAppForm.setAppFormLanguageTest(rs.getInt("LANGUAGETEST"));
				userAppForm.setAppFormLicences(rs.getInt("LICENCE"));
				//userAppForm.setAppFormForeignStudy(rs.getInt("FOREIGNSTUDIES"));
				userAppForm.setAppFormAwards(rs.getInt("AWARD"));
				userAppForm.setAppFormCompany(rs.getString("COMPANYNAME"));
				userAppForm.setAppFormRegisterName(rs.getString("REGISTERNAME"));
				userAppForm.setAppFormStartDate(rs.getTimestamp("REGISTERSTARTDAY"));
				userAppForm.setAppFormEndDate(rs.getTimestamp("REGISTERENDDAY"));
				userAppForm.setPreference(rs.getString("PREFERENCE"));
				userAppForm.setAppFormVolunteers(rs.getInt("VOLUNTEER"));
				userAppForm.setDetail(rs.getString("DETAIL"));
				userAppForm.setAppFormHighschool(rs.getString("HIGHSCHOOL"));
				userAppForm.setAppFormNum(AppFormNum);
			}
			else {
				return userAppForm;
			}
			
			sql = "select * from APPFORM_INFO where APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AppFormNum);
			rs = pstmt.executeQuery();
			if ( rs.next() ){
				AppFormUserInfo appFormUserInfo = new AppFormUserInfo();
				
				appFormUserInfo.setName1(rs.getString("NAME1"));
				appFormUserInfo.setName2(rs.getString("NAME2"));
				appFormUserInfo.setName3(rs.getString("NAME3"));
				appFormUserInfo.setBirthday(rs.getString("BIRTHDAY"));
				appFormUserInfo.setAddress(rs.getString("ADDRESS"));
				appFormUserInfo.setEmail(rs.getString("EMAIL"));
				appFormUserInfo.setPhone1(rs.getString("PHONE1"));
				appFormUserInfo.setPhone2(rs.getString("PHONE2"));	
				
				userAppForm.setAppFormUserInfo(appFormUserInfo);
			}
			else {
				return null;
			}
			
			sql = "select * from APPFORM_SELFINTRO where APPFORMNUM=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AppFormNum);
			rs = pstmt.executeQuery();
			ArrayList<SelfIntroduction> datas = new ArrayList<SelfIntroduction>();
			while ( rs.next() ){
				SelfIntroduction self = new SelfIntroduction();
				self.setIntroSize(rs.getInt("INTROSIZE"));
				self.setItemName(rs.getString("ITEMNAME"));
				self.setSelfIntroNum(rs.getInt("SELFINTRONUM"));
				
				datas.add(self);
			}
			
			userAppForm.setSelfIntroductions(datas);
			return userAppForm;
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
	
		return userAppForm;
		
	}
	
public ArrayList<AppFormDTO> AppFormList() throws SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		ArrayList<AppFormDTO> AppFormlist = null;
		String sql;
		AppFormDTO appForm;
		try {
			conn = ds.getConnection();
			sql = "select APPFORMNUM from APPFORM_REGISTER WHERE ROWNUM <= 10 order by APPFORMREGISTERDAY DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				AppFormlist = new ArrayList<AppFormDTO>();
				while(rs.next()) {
					appForm = getAppForm(rs.getInt("APPFORMNUM"));
					AppFormlist.add(appForm);
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
	
		
		return AppFormlist;
		
	}
	
public ArrayList<AppFormDTO> AllAppFormList() throws SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		ArrayList<AppFormDTO> AppFormlist = null;
		String sql;
		AppFormDTO appForm;
		try {
			conn = ds.getConnection();
			sql = "select APPFORMNUM from APPFORM_REGISTER order by APPFORMREGISTERDAY DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				AppFormlist = new ArrayList<AppFormDTO>();
				while(rs.next()) {
					appForm = getAppForm(rs.getInt("APPFORMNUM"));
					AppFormlist.add(appForm);
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
	
		
		return AppFormlist;
		
	}

public AppFormDTO getSearchAppForm(int AppFormNum, String keyword) throws SQLException {

	Connection conn = null;
	PreparedStatement pstmt = null;	
	ResultSet rs = null;
	AppFormDTO userAppForm = null;
	String sql;
	
	try {
		conn = ds.getConnection();
	
		sql = "select * from APPFORM where APPFORMNUM=? AND COMPANYNAME LIKE '%" + keyword + "%'"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, AppFormNum);
		rs = pstmt.executeQuery();
		if ( rs.next() ){
			userAppForm = new AppFormDTO();
			userAppForm.setAppFormUniversity(rs.getString("UNIVERSITY"));
			userAppForm.setAppFormCareers(rs.getInt("CAREER"));
			userAppForm.setAppFormLanguageLevel(rs.getInt("LANGUAGELEVEL"));
			userAppForm.setAppFormLanguageTest(rs.getInt("LANGUAGETEST"));
			userAppForm.setAppFormLicences(rs.getInt("LICENCE"));
			//userAppForm.setAppFormForeignStudy(rs.getInt("FOREIGNSTUDIES"));
			userAppForm.setAppFormAwards(rs.getInt("AWARD"));
			userAppForm.setAppFormCompany(rs.getString("COMPANYNAME"));
			userAppForm.setAppFormRegisterName(rs.getString("REGISTERNAME"));
			userAppForm.setAppFormStartDate(rs.getTimestamp("REGISTERSTARTDAY"));
			userAppForm.setAppFormEndDate(rs.getTimestamp("REGISTERENDDAY"));
			userAppForm.setPreference(rs.getString("PREFERENCE"));
			userAppForm.setDetail(rs.getString("DETAIL"));
			userAppForm.setAppFormHighschool(rs.getString("HIGHSCHOOL"));
			userAppForm.setAppFormNum(AppFormNum);
		}
		else {
			return userAppForm;
		}
		
		return userAppForm;
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

	return userAppForm;
}
}
