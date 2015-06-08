<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr" import="classes.*, appForm.*, java.util.*, java.text.SimpleDateFormat, java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement,
	java.sql.SQLException, java.io.ByteArrayOutputStream, java.io.FileInputStream, javax.imageio.*, java.sql.*, java.io.*, java.awt.image.BufferedImage, oracle.sql.BLOB"%>
<%
	request.setCharacterEncoding("euc-kr");
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ServletContext context = this.getServletContext();						
	String path = context.getRealPath("\\user");
	String realPath = path.toString();
	session.setAttribute("realPath", realPath);
%>

<jsp:useBean id="user" scope="session" class="user.UserDTO" />
<jsp:useBean id="userSpec" scope="request" class="userSpec.UserSpecDTO" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./Css/mainMyInfo.css" type="text/css"
	media=all />
<title>Insert title here</title>

<script>
	
</script>
</head>
<body bgcolor="#71ABFF">

	<div id="left"></div>
	<div id="right"></div>
	<div id="top"></div>
	<div id="bottom"></div>

	<table border="0" align="center" width="150" height="100"
		cellpadding="2" cellspacing="3">
		<tr height="11">
			<td colspan="2"></td>
		</tr>
		<tr>
			<td rowspan="4">
				<%
					String photoExisted = request.getAttribute("photoExisted").toString();
				
					if (photoExisted.equals("true")) {
						Connection conn = null;

						String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
						String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";

						int UserNum = user.getUserNum();
						String image_name = userSpec.getSpecUserInfo().getUserNum()
								+ ".jpg";

						try {
							Class.forName(jdbc_driver);
							conn = DriverManager.getConnection(db_url, "eanby",
									"eanby2012");
							/*create table mytable (bfile BLOB, cfile CLOB) 으로 테이블을 생성한 후에....
							 * 바이너리 데이터 컬럼에 바이너리 데이터를 저장한다.
							 */
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt
									.executeQuery("select USERPHOTO from SPEC_PHOTO where USERNUM="
											+ UserNum);

							while (rs.next()) {
								BLOB blob = (BLOB) rs.getBlob(1);

								InputStream instream = blob.getBinaryStream();
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

							rs.close();
							stmt.close();
							conn.commit();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}

						out.println("<img src=\"user" + image_name
								+ "\" width=70 height=80>");
					}
					else 
						out.print("<input type=\"image\" src=\"./Image/default_photo.png\" width=\"70\" height=\"80\">");
				%>
			</td>
		</tr>

		<tr>
			<td width="70"><%=user.getUserName()%>(<%=user.getUserNickname()%>)</td>
		</tr>
		<tr>
			<td>
				<%
					if (user.getManagerCheck() != null) {
				%> 매니저 <%
					} else {
				%> 회원 <%
					}
				%>
			</td>
		</tr>
		<tr>
			<td><a href="./user_spec_write.m2?command=mySpec"
				target="contents">My Spec</a></td>
		</tr>

	</table>

</body>
</html>
