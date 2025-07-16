<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>데이터 가져오기</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty result.testId}">
			<c:set var="actionUrl" value="/test/update.do"/>
		</c:when>
		<c:otherwise>
			<c:set var="actionUrl" value="/test/insert.do"/>
		</c:otherwise>
	</c:choose>
	
	<form action="${actionUrl}" method="post" name="testVO">
		<input type="hidden" name = "testId" value="${result.testId}"/>
		<label for="testCtgry">카테고리</label>
		<select id="testCtgry" name="testCtgry">
			<option value="수업1">수업1</option>
			<option value="수업2" <c:if test="${result.testCtgry eq '수업2'}">selected="selected"</c:if>>수업2</option>
			<option value="수업3" <c:if test="${result.testCtgry eq '수업3'}">selected="selected"</c:if>>수업3</option>
		</select>
		<br>
		<label for="testSj">제목</label>
		<input type="text" id="testSj" name="testSj" value="${result.testSj}"/>
		<br>
		<label for="testNm">작성자</label>
		<input type="text" id="testNm" name="testNm" value="${result.testNm}"/>
		<br>
		<label for="testCn">내용</label>
		<textarea id="testCn" name="testCn"><c:out value="${result.testCn}"/></textarea>
		<c:choose>
			<c:when test = "${not empty result.testId}">
				<button type="submit">수정</button>
			</c:when>
			<c:otherwise>
				<button type="submit">등록</button>
			</c:otherwise>
		</c:choose>
	</form>
</body>
</html>