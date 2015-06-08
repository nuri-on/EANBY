<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./Css/login.css" type="text/css" />

<script type="text/javascript">
	function clearTxt(txt) {
		if (txt.defaultValue == txt.value) {
			txt.value = "";
		}
	}

	function check() {
		//alert("User check !!");
		checkUser();
		//alert("......check !!");		
		return true;
	}

	function checkUser() {
		//alert(".../////check !!");	
		if (login_form.r[0].checked == true) {
			login_form.action.value = "login";
			//alert("User's login !!");

			return true;

		} else if (login_form.r[1].checked == true) {
			login_form.action.value = "Mlogin";
			//alert("Manager's login. !!");

			return true;
		}

		return false;
	}

	function eanbyLogin() {
		if (login_form.userEmail.value == "") {
			alert("사용자 아이디를 입력하십시요.");
			login_form.email.focus();
			return false;
		}
		if (login_form.password.value == "") {
			alert("비밀번호를 입력하십시요.");
			login_form.password.focus();
			return false;
		}

		if (login_form.r[0].checked == true) {
			login_form.command.value = "login";
			//alert("User's login !!");

		} else if (login_form.r[1].checked == true) {
			login_form.command.value = "Mlogin";
			//alert("Manager's login. !!");
		}

		login_form.action = "login.m2";
		login_form.submit();
	}

	function userJoin() {
		login_form.action = "user_write.jsp";
		login_form.submit();
		//location.href = "user_write.jsp";

	}

	function userCreate() {
		f.action = "user_write.jsp";
		f.submit();
	}
</script>


</head>
<body id="loginPage" bgcolor="#71ABFF">
	<div id="enterLogo">
		<span><a href="./login.jsp">Enter Site</a></span>
	</div>
	<c:if test="${not empty exception}">
		<c:out value="${exception.getMessage()}" />
	</c:if>
	<div id="enterForm">
		<center>
			<form name="login_form" method="post">
				<input type="hidden" name="command" /> <input type="radio" name="r"
					value="1" checked><font color="WHITE">USER</font>
				&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="r" value="2"><font
					color="WHITE">MANAGER </font><br><br>
				<table>
					<tr>
						<!-- 이메일 입력 -->
						<td width="200" align="center"><font color="WHITE">E-MAIL</font></td>
						<td align="left"><input type="text" name="userEmail" size="30"
							value="아이디를 입력하세요."
							onFocus="clearTxt(login_form.userEmail)"></td>
					</tr>
					<tr>
						<!-- 패스워드 입력 -->
						<td width="200" align="center"><font color="WHITE">PASSWORD</font></td>
						<td align="left"><input type="password" name="password" size="31"
						></td>
					</tr>
				</table>
				<table>

					<tr>
						<!-- 버튼 -->
						<td align="right" >
						<input type="button"
							name="login" value="Login" width="10" onClick="eanbyLogin()" style="width=:50px;height:27px;font-size:15px;">
							&nbsp;&nbsp;&nbsp;
						<input type="button" name="join" style="width:50px;height:27px; font-size:15px;"
							value="Join" onclick="userJoin()">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>
</body>
</html>