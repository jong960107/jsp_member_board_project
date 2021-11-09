<%@page import="model.BoardBean"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="bean" class="model.BoardBean">

</jsp:useBean>

<%

int num = Integer.parseInt(request.getParameter("num").trim());


BoardDAO bdao = new BoardDAO();

BoardBean mbean = bdao.getOneBoard(num);


%>

<center>

<h2>게시글 보기 </h2>

<table width="600" border ="1" bgcolor="pink">

<tr height="40">
<td align="center" width="120">글번호 </td>
<td align="left" width="180"><%=mbean.getNum() %> </td>
<td align="center" width="100">조회수  </td>
<td align="left" width="180"><%=mbean.getReadcount() %></td>
</tr>


<tr height="40">
<td align="center" width="120">작성자  </td>
<td align="left" width="180"><%=mbean.getWriter() %> </td>
<td align="center" width="100">작성일   </td>
<td align="left" width="180"><%=mbean.getReg_date() %></td>
</tr>


<tr height="40">
<td align="center" width="120">이메일   </td>
<td align="center" colspan="3"><%=mbean.getEmail()%> </td>
</tr>

<tr height="40">
<td align="center" width="120">제목   </td>
<td align="center" colspan="3"><%=mbean.getSubject()%> </td>
</tr>

<tr height="40">
<td align="center" width="120">글내용   </td>
<td align="center" colspan="3"><%=mbean.getContent()%> </td>
</tr>

<tr height="40">
<td align="center" colspan="4">
<input type="button" value="답글쓰기" 
onclick = "location.href='BoardReWriteForm.jsp?num=<%=mbean.getNum() %>&ref=<%=mbean.getRef()%>&re_step=<%=mbean.getRe_step()%>&re_level=<%=mbean.getRe_level()%>' ">
<input type="button" value="수정하기 "onclick = "location.href = 'BoardUpdateForm.jsp?num=<%=mbean.getNum()%>' " />
<input type="button" value="삭제하기 "onclick = "location.href = 'BoardDeleteForm.jsp?num=<%=mbean.getNum() %>' "/>
<input type="button" value="목록보기 "onclick = "location.href = 'BoardList.jsp' "/>
</td>
</tr>

</table>

</center>



</body>
</html>