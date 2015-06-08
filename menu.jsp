<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function reShow() {
		parent.left_items.location.reload();
		parent.contents.location.reload();
	}
</script>
</head>
<body bgcolor="#71ABFF">
	<a><img src="./Image/logo.png" width="200" onClick=""></a> &nbsp;&nbsp;&nbsp;<a href="./login.m2?command=logout" target="_parent">LOGOUT</a>      &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	<a href="./main_contents.m2?command=appList" target="contents"><img src="./Image/top_menu1.png" width="50" height="50" border='0'></a>

</body>
</html>