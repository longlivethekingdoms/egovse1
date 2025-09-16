<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Language" content="ko"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<title><c:out value="${param.title}"/></title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<c:choose>
		<c:when test="${not empty param.css}">
			<c:if test="${param.commonCssUseAt eq 'Y'}">
			<!-- BBS Style '0' 12개 -->
			<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet"/>
			<!-- 공통 Style '0' 13개 -->
			<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet"/>
			<link href="/asset/TYPETMP_02/style.css" rel="stylesheet"/>
			</c:if>
			<link href="${param.css}" rel="stylesheet"/>
		</c:when>
		<c:otherwise>
			<!-- BBS '0' 12개 -->
			<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet"/>
			<!-- 공통 Style '0' 13개 -->
			<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet"/>
			<link href="/asset/TYPETMP_02/style.css" rel="stylesheet"/>
		</c:otherwise>
	</c:choose>
</head>
<body>
	<c:if test="${param.headerUseAt ne 'N'}">
		<header class="site-header">
			<div class="util">
				<div class="wrap">
					<ul>
						<li><a href="/">HOME</a></li>
						<c:choose>
							<c:when test="${empty USER_INFO.id}">
								<li><a href="/login/login.do">로그인</a></li>
								<li><a href="/join/siteUseAgree.do">회원가입</a></li>
							</c:when>
							<c:otherwise>
								<li><strong><c:out value="${USER_INFO.name}"/></strong>님 환영합니다</li>
								<li><a href="/login/actionLogout.do">로그아웃</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>	
			</div>
			
			<div class="header-cont wrap">
				<nav class="site-gnb">
					<ul>
						<li>
							<a href="/board/selectList.do?boardType=NORMAL">게시판</a>
						</li>
						<li>
							<a href="/board/selectList.do?boardType=IMAGE">이미지 게시판</a>
						</li>
						
						<c:if test="${USER_INFO.id eq 'admin' }">
							<li>
								<a href="/admin/member/memberList.do">회원관리</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</header>
	</c:if>
