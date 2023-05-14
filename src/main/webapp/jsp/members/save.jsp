<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
// request, response 사용 가능 서블릿과 다르게 그냥 request response를 가져다쓰면됨
//<%로 자바 로직 넣는 공간 만들기
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
성공
<ul>
<li>id=<%=member.getId()%></li>
<li>username=<%=member.getUsername()%></li>
<li>age=<%=member.getAge()%></li>
</ul>
<a href="/">메인</a>
</body>
</html>