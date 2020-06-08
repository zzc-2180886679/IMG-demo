<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String ctxPath=request.getContextPath(); %>
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--
enctype="multipart/form-data"属性
text/plain ---纯文本传输，不能用于上传文件
application/x-www-form-urlencoded  ---默认值,作用是设置表单传输的编码,不能用于上传文件
multipart/form-data ---制定传输数据的特殊类型,上传的非文本的内容，比如图片或是是mp3
-->
<form action="<%=ctxPath%>/upload" method="post" enctype="multipart/form-data">
<input  type="file" name="images"> 
<button  type="submit"  name="upload">上传</button>
</form>
</body>
</html>
