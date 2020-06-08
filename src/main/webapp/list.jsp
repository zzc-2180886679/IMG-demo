<%@page import="domain.Imgpojo"%>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
String ctxPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片展示</title>
<script type="text/javascript">
</script>
</head>
<body>
<div align="center"><a href="index.jsp">添加</a></div>
	<form action="" method="post">
		<table laign="center" border="2px">
		<tr>
			<td>图片列表</td>
		</tr>
		<tr align="center">
			<td>图片编号</td>
			<td>图片</td> 
			<td>操作</td>
		</tr>
		<%
		ArrayList<Imgpojo> list = (ArrayList<Imgpojo>)request.getAttribute("list"); 
   		 %>
   		 <%
   		for (int i = 0; i < list.size(); i++)
   		{
   			%>
   			<tr align="center">
   			<td><%=list.get(i).getId()%></td>
   			<td><img src="<%=list.get(i).getP_image()%>"></td>
   			<td>
   			<a href="<%=ctxPath%>/deleteimg?pid=<%=list.get(i).getId()%>&imgname=<%=list.get(i).getP_image() %>">删除</a>
   			</td>
   		</tr>
   			<% 
   		}
   		 %>
   		
		</table>
	
	</form>
</div>

</body>
</html>