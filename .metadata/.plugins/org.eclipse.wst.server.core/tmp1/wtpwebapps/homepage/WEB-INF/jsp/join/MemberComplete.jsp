<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/template/header.do" charEncoding="utf-8">
	<c:param name="title" value="회원가입"/>
	<c:param name="css" value="/asset/member/css/login.css"/>
	<c:param name="headerUseAt" value="N"/>
</c:import>

<div class="tit_intro_step">
	<ul>
		<li>약관동의</li>
		<li>회원유형</li>
		<li>정보입력</li>
		<li class="current">가입완료</li>
	</ul>
</div>

<h2 class="icon1">가입완료</h2>
<p class="mB40">회원가입이 완료되었습니다. 감사합니다.</p>
<div class="join_finish">
	<p>"회원가입이 완료되었습니다."<em>로그인 후</em> 사용해 주시기 바랍니다."</p>
	<div class="btn_c">
		<a href="/login/login.do" class="btn-lg spot">로그인</a>
		<a href="/board/selectList.do" class="btn-lg">메인으로</a>
	</div>
</div>
<c:import url="/template/footer.do" charEncoding="utf-8"/>