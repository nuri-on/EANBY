<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	ServletContext context = this.getServletContext();						
	String path = context.getRealPath("\\user");
	String realPath = path.toString();
	session.setAttribute("realPath", realPath);
%>
<script type="text/javascript">
	window.name="parentWin";
</script>
<title></title>
</head>
	<frameset rows ="80, *" border=0>
		<frame name="top_menu" src="./menu.jsp" scrolling=no>
		
		<frameset cols="200, *" border="0">
			<frameset rows="150, *" border=0>
				<frame name="my_info" src="./my_info.m2?command=getPhoto" border=0>
				<frame name="left_items" src="./left_items.m2?command=userAppList" scrolling="yes">
			</frameset>
			<frame name="contents" src="./main_contents.m2?command=appList">
		</frameset>
	</frameset>
</html>