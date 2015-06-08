<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"
	import="classes.*, appForm.*, java.util.*, java.text.SimpleDateFormat, java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement,
	java.sql.SQLException, java.io.ByteArrayOutputStream, java.io.FileInputStream, javax.imageio.*, java.sql.*, java.io.*, java.awt.image.BufferedImage, oracle.sql.BLOB"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

</head>
<body>
	<center>
		<h1>${appForm.getAppFormRegisterName()}-
			${appForm.getAppFormCompany()}</h1>
		<form name="appFormRegister" method="post" action="main_insertApp.m2">
			<input type="hidden" name="command" value="userAppInsert"> <input
				type="hidden" name="AppFormNum" value="${appForm.getAppFormNum()}">

			<input type="submit" name="btn1" value="등록하기"> <Br> <Br>
			<%
				String appFormNo = request.getParameter("AppFormNum");
				int AppFormNum = Integer.parseInt(appFormNo);

				// 이미지 처리!
				Connection conn = null;

				String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
				String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
				String realPath = null, image_name = null, path = null;

				try {
					Class.forName(jdbc_driver);
					conn = DriverManager
							.getConnection(db_url, "eanby", "eanby2012");
					/*create table mytable (bfile BLOB, cfile CLOB) 으로 테이블을 생성한 후에....
					 * 바이너리 데이터 컬럼에 바이너리 데이터를 저장한다.
					 */
					Statement stmt = conn.createStatement();
					ResultSet rst = stmt.executeQuery("SELECT APPFORM_POSTER FROM APPFORM_POSTER WHERE APPFORMNUM=" + AppFormNum);
					
					while (rst.next()) {
						BLOB blob = (BLOB)rst.getBlob(1);

						InputStream instream = blob.getBinaryStream();
						image_name = AppFormNum + ".jpg";
						ServletContext context = this.getServletContext();						
						path = context.getRealPath("\\poster");
						realPath = path.toString();
						FileOutputStream outstream = new FileOutputStream(path + image_name);

						int size = blob.getBufferSize();
						byte[] buffer = new byte[size];
						int length = -1;

						while ((length = instream.read(buffer)) != -1) {
							outstream.write(buffer, 0, length);
						}

						outstream.close();
						instream.close();
					}
					
					rst.close();
					stmt.close();
					conn.commit();
					conn.close();
				} catch(Exception e) {
					System.err.println(e);
				}
			%>
			<% String printPoster = "poster" + image_name;%>			
			<img src="<%=printPoster %>"><br><br>
			<input type="submit" name="btn2" value="등록하기">
		</form>

	</center>
</body>
</html>