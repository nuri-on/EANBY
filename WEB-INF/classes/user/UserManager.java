package user;

import java.sql.SQLException;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(UserDTO user) throws SQLException, ExistedUserException {
		if (userDAO.existedUser(user.getUserEmail())) {
			throw new ExistedUserException(user.getUserEmail() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	public int update(UserDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(int UserNum) throws SQLException {
		return userDAO.remove(UserNum);
	}

	public UserDTO findUser(String userEmail)
		throws SQLException, UserNotFoundException {
		UserDTO user = userDAO.findUser(userEmail);
		
		if (user == null) {
			throw new UserNotFoundException(userEmail + "는 존재하지 않는 이메일입니다.");
		}		
		return user;
	}

//	public List<UserDTO> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findUserList(currentPage, countPerPage);
//	}

	public boolean login(String userEmail, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		UserDTO user = findUser(userEmail);
		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		
		return true;
	}
	
	public boolean mlogin(String userEmail, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
			UserDTO user = findUser(userEmail);
			if (!user.getManagerCheck().equals("y")) {
				if (!user.isMatchPassword(password)) 
					throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
				throw new PasswordMismatchException("Manager가 아닙니다.");
			}
			
			return true;
		}
//	private UserDAO getUserDAO(){
//		try {
//			return new UserDAO();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
	//public void setUserDAO(UserDAO userDAO) {
		//this.userDAO = userDAO;
	//}
}
