<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>게시글 수정</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td><c:out value="${result.testCtgry}"/>
						<c:out value="${result.testSj}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${result.testNm}"/></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${result.testFrstRegistPnttm}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${result.testCn}"/></td>
		</tr>
	</tbody>
</table>
	<div class="box-btn">
		<c:url var="uptUrl" value="/test/testRegist.do">
			<c:param name="testId" value="${result.testId}"/>
		</c:url>
		<a href="${uptUrl}">수정</a>
		
		<c:url var="delUrl" value="/test/delete.do">
			<c:param name="testId" value="${result.testId}"/>
		</c:url>
		<a href="${delUrl}" class="btn-del">삭제</a>
		<a href="/test/selectList.do">목록</a>
	</div>
	<script>
	$(document).ready(function(){
		$(".btn-del").click(function(){
			if(!confirm("삭제하시겠습니까?"){
				return false;
			})
		});
	});
	</script>
</html>