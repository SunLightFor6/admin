<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<h3 align="left">
<a href="test/TestSaveTeacher">TestSaveTeacher</a>
<form action="test/TestSaveTeacher" method="post" enctype="multipart/form-data">
	<input type="file" name="upload" multiple="multiple"/><!-- multiple属性可以支持文件多选 -->
	<button type="submit">提交</button>
</form>
</h3>

</body>
</html>