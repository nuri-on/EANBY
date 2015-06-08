<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"
	import="classes.*, appForm.*, java.util.*, java.text.SimpleDateFormat"%>
<%
	request.setCharacterEncoding("euc-kr");
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="./Css/manager.css" type="text/css" />

<script type="text/javascript">
	function appInsert() {
		appform_register.command.value = "appInsert";
		appform_register.action = "main_manager_contents.m2";
		alert("��ϵǾ����ϴ�.");
		appform_register.submit();

	}

	var selfIntroFormCnt = 0;
	function addSelfIntros() {
		++selfIntroFormCnt;
		msg = "<table width='500'><tr><td id='Rid' width='120'>&nbsp;&nbsp;"
		+ selfIntroFormCnt 
		+ "&nbsp;&nbsp;</td><td id='Rid'><input type='text' name='selfIntro' size='25'/>&nbsp;&nbsp;<input type='text' name='selfIntroBytes' size='8' /></td></tr></table>";
		
		selfIntroForm.innerHTML += msg;
	}

	$(".btn_add").click(function() {
		var data = "<tr><td>content</td></tr>";
		$("#tab;e").append(data);
	});
	
	// �̹��� ó��!
	function previewImage(targetObj, View_area) {
		var preview = document.getElementById(View_area); //div id
		var ua = window.navigator.userAgent;

	  //ie�϶�(IE8 ���Ͽ����� �۵�)
		if (ua.indexOf("MSIE") > -1) {
			targetObj.select();
			try {
				var src = document.selection.createRange().text; // get file full path(IE9, IE10���� ��� �Ұ�)
				var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


				if (ie_preview_error) {
					preview.removeChild(ie_preview_error); //error�� ������ delete
				}

				var img = document.getElementById(View_area); //�̹����� �ѷ��� ��

				//�̹��� �ε�, sizingMethod�� div�� ���缭 ����� �ڵ����� �ϴ� ����
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
			} catch (e) {
				if (!document.getElementById("ie_preview_error_" + View_area)) {
					var info = document.createElement("<p>");
					info.id = "ie_preview_error_" + View_area;
					info.innerHTML = e.name;
					preview.insertBefore(info, null);
				}
			}
	  //ie�� �ƴҶ�(ũ��, ���ĸ�, FF)
		} else {
			var files = targetObj.files;
			for ( var i = 0; i < files.length; i++) {
				var file = files[i];
				var imageType = /image.*/; //�̹��� �����ϰ�츸.. �ѷ��ش�.
				if (!file.type.match(imageType))
					continue;
				var prevImg = document.getElementById("prev_" + View_area); //������ �̸����Ⱑ �ִٸ� ����
				if (prevImg) {
					preview.removeChild(prevImg);
				}
				var img = document.createElement("img"); 
				img.id = "prev_" + View_area;
				img.classList.add("obj");
				img.file = file;
				img.style.width = '100px'; 
				img.style.height = '100px';
				preview.appendChild(img);
				if (window.FileReader) { // FireFox, Chrome, Opera Ȯ��.
					var reader = new FileReader();
					reader.onloadend = (function(aImg) {
						return function(e) {
							aImg.src = e.target.result;
						};
					})(img);
					reader.readAsDataURL(file);
				} else { // safari is not supported FileReader
					//alert('not supported FileReader');
					if (!document.getElementById("sfr_preview_error_"
							+ View_area)) {
						var info = document.createElement("p");
						info.id = "sfr_preview_error_" + View_area;
						info.innerHTML = "not supported FileReader";
						preview.insertBefore(info, null);
					}
				}
			}
		}
	}
</script>
</head>

<body>
	<center>
		<h1>������</h1>
		<br> <br> <br>
		<form name="appform_register" method="post" action="main_manager_contents.m2">
			<table width="500" >
				<tr>
					<td id="Rid" width="250">������ ���</td>
					<td id="Rid"><div id='View_area' style='position:relative; width: 100px; height: 100px; color: black; border: 0px solid black; dispaly: inline; '></div>
						<input type="file" name="profile_pt" id="profile_pt" onchange="previewImage(this,'View_area')">
				</tr>
				<tr>
					<td id="Rid">�����̸�</td>
					<td id="Rid"><input type="text" name="registerName" />
				</tr>
				<tr>
					<td id="Rid">ȸ���</td>
					<td id="Rid"><input type="text" name="companyName" /></td>
				</tr>
				<tr>
					<td id="Rid">��ä ���� ��¥</td>
					<td id="Rid"><input type="date" name="appStartDay" /></td>
				</tr>
				<tr>
					<td id="Rid">��ä ���� ��¥</td>
					<td id="Rid"><input type="date" name="appEndDay" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ� ���� Ƚ��</td>
					<td id="Rid"><input type="text" name="appAwardCount" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ� ȸȭ ���� Ƚ��</td>
					<td id="Rid"><input type="text" name="appLanguageLevelCount" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ� ���� �ڰ��� ����</td>
					<td id="Rid"><input type="text" name="appLanguageTestCount" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ� �ڰ��� ����</td>
					<td id="Rid"><input type="text" name="appLicenceCount" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ����Ȱ�� ����</td>
					<td id="Rid"><input type="text" name="appVolunteerCount" /></td>
				</tr>
				<tr>
					<td id="Rid">�ִ� ��� ����</td>
					<td id="Rid"><input type="text" name="appCareerCount" /></td>
				</tr>
				<tr>
					<td id="Rid">���б� ����</td>
					<td id="Rid"><input type="checkbox" name="appUniversityC"></td>
				</tr>
				<tr>
					<td id="Rid">����б� ����</td>
					<td id="Rid"><input type="checkbox" name="appHighschoolC"></td>
				</tr>
				<tr>
					<td id="Rid">�̸�1 ����</td>
					<td id="Rid"><input type="checkbox" name="appName1C"></td>
				</tr>
				<tr>
					<td id="Rid">�̸�2 ����</td>
					<td id="Rid"><input type="checkbox" name="appName2C"></td>
				</tr>
				<tr>
					<td id="Rid">�̸�3 ����</td>
					<td id="Rid"><input type="checkbox" name="appName3C"></td>
				</tr>
				<tr>
					<td id="Rid">���� ����</td>
					<td id="Rid"><input type="checkbox" name="appBirthdayC"></td>
				</tr>
				<tr>
					<td id="Rid">�ּ� ����</td>
					<td id="Rid"><input type="checkbox" name="appAddressC"></td>
				</tr>
				<tr>
					<td id="Rid">�̸��� ����</td>
					<td id="Rid"><input type="checkbox" name="appEmailC"></td>
				</tr>
				<tr>
					<td id="Rid">��ȭ1 ����</td>
					<td id="Rid"><input type="checkbox" name="appPhone1C"></td>
				</tr>
				<tr>
					<td id="Rid">��ȭ2 ����</td>
					<td id="Rid"><input type="checkbox" name="appPhone2C"></td>
				</tr>
				<tr>
					<td id="Rid">��ȣ����</td>
					<td id="Rid"><input type="text" name="appPreference" /></td>
				</tr>
				<tr>
					<td id="Rid">���λ���</td>
					<td id="Rid"><input type="text" name="appDetail" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><table>
							<table width="500">
								<tr>
									<td id="Rid" width='120'>�ڱ�Ұ���</td>
									<td  id="Rid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�׸�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ִ�byte&nbsp;&nbsp;&nbsp;<input
						type="button" value="�߰�" onclick="addSelfIntros()" /></td>
								</tr>
							</table>
							<div id="selfIntroForm"></div>
				</tr>
			</table>
			<input type="hidden" name="command" value="appInsert" /> <input type="hidden" name="action" /> <input type="submit" value="���� ���">
		</form>

	</center>
</body>


</html>