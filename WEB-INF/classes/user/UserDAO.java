package user;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	private DataSource ds;
	
	public UserDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	public int create(UserDTO user) throws SQLException {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into EANBY_USER(USERNAME, USEREMAIL, USERPHONE1, USERPHONE2, USERBIRTH, USERNICKNAME, USERPASSWORD)"
					+ "values(?,?,?,?,?,?,?)";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPhone1());
			pstmt.setString(4, user.getUserPhone2());
			if (user.getUserBirth() != null)
				pstmt.setDate(5, new java.sql.Date(user.getUserBirth().getTime()));
			else
				pstmt.setDate(5, null);
			pstmt.setString(6,  user.getUserNickname());
			pstmt.setString(7, user.getUserPassword());
			
			int result = pstmt.executeUpdate();
			return result;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( conn != null ){
				conn.close();
			}
		}
	}
	
	
	public int update(UserDTO user) throws SQLException {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update EANBY_USER set USERNAME=?, USEREMAIL=?, USERPHONE1=?, USERPHONE2=?, USERBIRTH=?, USERNICKNAME=?, USERPASSWORD=? where USERNUM=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPhone1());
			pstmt.setString(4, user.getUserPhone2());
			if (user.getUserBirth() != null)
				pstmt.setDate(4, new java.sql.Date(user.getUserBirth().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(6,  user.getUserNickname());
			pstmt.setString(7, user.getUserPassword());
			pstmt.setInt(8, user.getUserNum());
			
			int result = pstmt.executeUpdate();
			
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
	
	
	public int remove(int UserNum) throws SQLException {		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		String sql = "delete from EANBY_USER where USERNUM=?";
		 
		 try {
			 
			 conn = ds.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, UserNum);
			 
			 int result = pstmt.executeUpdate();
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
	
	public boolean existedUser(String UserEmail) throws SQLException {	
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		String sql = "select USEREMAIL from EANBY_USER where USEREMAIL=?";
		
		try {
			 conn = ds.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, UserEmail);
			 rs = pstmt.executeQuery();
			 
			 int count = 0;
			 if ( rs.next()) {
				count = rs.getInt(1);
			 }
			 if ( count == 1 ) {
				 return true;
			 } 
			 else {
				return false;
			 }
		 } 
		 
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( conn != null ) {
				conn.close();
			}
		 }
	}
	
	
		
	public boolean existedNickname(String UserNickname) throws SQLException {	
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		String sql = "select USEREMAIL from EANBY_USER where USERNICKNAME=?";
		
		try {
			 conn = ds.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, UserNickname);
			 rs = pstmt.executeQuery();
			 
			 int count = 0;
			 if ( rs.next()) {
				count = rs.getInt(1);
			 }
			 if ( count == 1 ) {
				 return true;
			 } 
			 else {
				return false;
			 }
		 } 
		 
		finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( conn != null ) {
				conn.close();
			}
		 }
		
	}
	
	
	public UserDTO findUser(String USEREMAIL) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO user = null;
		String sql = "select * from EANBY_USER where USEREMAIL=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USEREMAIL);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){
				user = new UserDTO();
				user.setUserNum(rs.getInt("USERNUM"));
				user.setUserName(rs.getString("USERNAME"));
				user.setUserEmail(USEREMAIL);
				user.setUserPhone1(rs.getString("USERPHONE1"));
				user.setUserPhone2(rs.getString("USERPHONE2"));
				user.setUserBirth(rs.getDate("USERBIRTH"));
				user.setUserNickname(rs.getString("USERNICKNAME"));
				user.setUserPassword(rs.getString("USERPASSWORD"));
				user.setManagerCheck(rs.getString("MANAGERCHECK"));
				
			}
			return user;
		
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
}

