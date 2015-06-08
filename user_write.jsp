<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr" %>
<% request.setCharacterEncoding("euc-kr"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="./Css/join.css" type="text/css" />

<script>
	function checkEmail() {
		f.command.value = "checkEmail";
		f.action = "user_write.m2";
		f.submit();
	}
	
	function userCreate() {
		if (f.UserEmail.value == "") {
			alert("이메일을 입력하십시요.");
			f.UserEmail.focus();
			return false;
		}
		if (f.UserPassword.value == "") {
			alert("비밀번호를 입력하십시요.");
			f.UserPassword.focus();
			return false;
		}
		if (f.UserName.value == "") {
			alert("이름을 입력하십시요.");
			f.UserName.focus();
			return false;
		}
		
		if (f.UserPhone1.value == "") {
			alert("핸드폰 번호를 입력하십시요.");
			f.UserPhone1.focus();
			return false;
		}
		if (f.UserBirth.value == "") {
			alert("생년월일을 입력하십시요.");
			f.UserBirth.focus();
			return false;
		}
		if (f.UserNickname.value == "") {
			alert("닉네임을 입력하십시요.");
			f.UserNickname.focus();
			return false;
		}
		
		f.command.value = "joinInsert";
		f.action = "user_write.m2";
		alert('가입되었습니다.');
		f.submit();
	}
	
	
	
</script>
</head>

<body bgcolor="#71ABFF" leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >
	<div id="enterLogo">
		<span><a href="./login.jsp">Enter Site</a></span>
	</div>
	<center>

	<br><br>

	<!--contents-->
<!-- 	<font style="font-size:30px; font-family:Arial; color:#FFFFFF;"><b>회원 가입</b></font> -->
					
	<br> <c:if test="${not empty exception}">
			<c:out value="${exception.getMessage()}" />
		 </c:if> <!-- write Form  -->
				
				<form name="f" method="post">
					<input type="hidden" name="command" />
					<table cellpadding="0" cellspacing="1" >
						<tr>
							<td bgcolor="#71ABFF" align=right width="200" height="40">Email(아이디)</td>
							<td width=250 style="padding-left: 10">
							<input type="text" style="width: 150" name="UserEmail">
							</td>
							<td style="align:left"><input type="button" value="중복검사" name="emailCheckBtn" onClick="checkEmail()" >
							</td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right height="40">NickName</td>
							<td width=250  style="padding-left: 10"><input
								type="text" style="width: 150" name="UserNickname"></td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right height="40">Password</td>
							<td width=250 style="padding-left: 10"><input
								type="password" style="width: 150" name="UserPassword"></td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right height="40">Name</td>
							<td width=250 style="padding-left: 10"><input
								type="text" style="width: 150" name="UserName"></td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right height="40">Mobile Phone</td>
							<td width=250 style="padding-left: 10"><input
								type="text" style="width: 150" name="UserPhone1"></td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right  height="40">Home Phone</td>
							<td width=250  style="padding-left: 10"><input
								type="text" style="width: 150" name="UserPhone2"></td>
						</tr>
						<tr>
							<td bgcolor="#71ABFF" width="200" align=right height="40">BirthDay</td>
							<td width=250  style="padding-left: 10"><input
								type="text" style="width: 150" name="UserBirth"></td>
						</tr>
					</table>
				</form> <br>
		
				<table width=400 border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td align=right style="border: 0px"> <input type="button"
							onClick="userCreate()" style="width:50px;height:27px; font-size:15px;"
							value="Join"> &nbsp;</td>
					</tr>
				</table>


</center>
</body>
</html>