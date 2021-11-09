<%@page import="java.io.PrintWriter"%>
<%@page import="model.MemberBean"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	MemberBean bean = new MemberBean();

	
	String id = request.getParameter("id");
	bean.setId(id);
	String password = request.getParameter("password");
	bean.setPassword(password);
	
	

	
	MemberDAO memberDAO = new MemberDAO();
	int result = memberDAO.login(bean.getId(),bean.getPassword());
	
	if(result == 1){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='../Main/index.jsp' ");
		script.println("</script>");
		session.setAttribute("id", id);
	}else if(result==0){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}
else if(result==-1){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}else if(result==-1){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스오류가 발생했습니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}
	
	%>


</body>
</html>